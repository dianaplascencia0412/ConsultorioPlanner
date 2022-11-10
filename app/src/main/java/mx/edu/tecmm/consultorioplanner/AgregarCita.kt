package mx.edu.tecmm.consultorioplanner

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AgregarCita : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    lateinit var txtId: TextView
    lateinit var txtNombreDoctor: TextView
    lateinit var txtFecha: TextView
    lateinit var spHora : Spinner
    lateinit var Hora : String
    lateinit var txtNombrePaciente: EditText
    lateinit var txtTelefono: EditText
    lateinit var db :consultorioplanner
    lateinit var fechahoy: String

    var  HorasDisponibles = arrayListOf("08:00 am", "09:00 am","10:00 am","11:00 am","12:00 pm","01:00 pm","02:00 pm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cita)

        db = this.application as consultorioplanner
        supportActionBar?.hide()
        txtId = findViewById(R.id.txtIdDoctorAC)
        txtNombreDoctor = findViewById(R.id.txtNombreD)
        txtFecha = findViewById(R.id.edFecha)
        spHora = findViewById(R.id.spinnerHora)
        txtNombrePaciente = findViewById(R.id.edNombrePaciente)
        txtTelefono = findViewById(R.id.edTelefonoPaciente)

        val hoy = System.currentTimeMillis()
        val fecha = Date(hoy)
        val df: DateFormat = SimpleDateFormat("dd / MM / yyyy")
        fechahoy = df.format(fecha)
        Log.e("salidafecha", "fecha:  $fechahoy")


        var id = intent.getStringExtra("idDoctor2")
        txtId.text = id


        var fechita = intent.getStringExtra("fecha")
        Log.e("fechita", "fecha:  $fechita")

        var fechahoynueva = fechahoy.toString().replace("\\s".toRegex(), "")
        var fechanueva = fechita.toString().replace("\\s".toRegex(), "")
        val formmater = SimpleDateFormat("dd/MM/yyyy")
        val date = formmater.parse(fechanueva)
        val formmaterhoy = SimpleDateFormat("dd/MM/yyyy")
        val datehoy = formmaterhoy.parse(fechahoynueva)

        if(date.after(datehoy)){
            txtFecha.text = fechita
        }
        else if(date.before(datehoy)){
            txtFecha.text = fechahoy
        }else if (date.equals(datehoy)){
            txtFecha.text = fechita
        }


        lifecycleScope.launch{
            val doctor = db.room.DoctoresDao().getById(txtId.text.toString())
            txtNombreDoctor.text = doctor.nombre
        }

        validarhoracita(txtId.text.toString() ,txtFecha.text.toString())


        txtFecha.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
            showDataPickerDialog()

        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
        Log.e("SPINNER", "Se selecciono algo: $posicion")
        Hora = HorasDisponibles[posicion]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun showDataPickerDialog(){
        val newFragment = DatePickerDateFragment2.newInstance(
            DatePickerDialog
            .OnDateSetListener { _, year, month, day ->
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)

                val hoy = System.currentTimeMillis()
                val fecha = Date(hoy)
                val df: DateFormat = SimpleDateFormat("dd / MM / yyyy")
                fechahoy = df.format(fecha)

                var fechahoynueva = fechahoy.replace("\\s".toRegex(), "")
                var fechaseleccionada = selectedDate.replace("\\s".toRegex(), "")

                val formmater = SimpleDateFormat("dd/MM/yyyy")
                val date = formmater.parse(fechaseleccionada)
                val formmaterhoy = SimpleDateFormat("dd/MM/yyyy")
                val datehoy = formmaterhoy.parse(fechahoynueva)


                if(date.after(datehoy)){
                    txtFecha.setText(selectedDate)
                }
                else if(date.before(datehoy)){
                    txtFecha.setText(fechahoy)
                }else if (date.equals(datehoy)){
                    txtFecha.setText(fechahoy)
                }

                actualizarspinner()
            })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun agregarCita(v: View) {

        //Aqui agrego una cita a la base de datos
        val idDoctor= txtId.text.toString()
        val fecha = txtFecha.text.toString()
        val hora = Hora.toString()
        val nombreP = txtNombrePaciente.text.toString()
        val telefono = txtTelefono.text.toString()
        if(idDoctor.equals("") or fecha.equals("") or nombreP.equals("") or telefono.equals("")){
            Toast.makeText(this, "Error complete todos los datos", Toast.LENGTH_LONG).show()
        }
        else {
            lifecycleScope.launch {
                val cita = Citas(0, fecha , hora , idDoctor, nombreP, telefono)
                db.room.CitasDao().insert(cita)
            }
            Toast.makeText(this, "Cita Registrada", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun validarhoracita(id:String,fecha:String){

        lifecycleScope.launch{
            val horasocupadas = db.room.CitasDao().getByHora(id ,fecha)
            val arrayOcupados = horasocupadas.map { it.horaCita }
            HorasDisponibles.removeAll{
                it in arrayOcupados
            }
            Log.e("ahoras", "arreglos horas:  $arrayOcupados , $HorasDisponibles")
            llenarspinner()
        }


    }
    fun llenarspinner(){
        val adaptador = ArrayAdapter(this ,android.R.layout.simple_spinner_item,HorasDisponibles)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spHora.adapter = adaptador
        spHora.onItemSelectedListener = this
    }
    fun actualizarspinner(){
        HorasDisponibles = arrayListOf("08:00 am", "09:00 am","10:00 am","11:00 am","12:00 pm","01:00 pm","02:00 pm")
        validarhoracita(txtId.text.toString() , txtFecha.text.toString())

    }

}