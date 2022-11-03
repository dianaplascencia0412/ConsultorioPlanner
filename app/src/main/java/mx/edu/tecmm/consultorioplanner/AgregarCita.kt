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

class AgregarCita : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    lateinit var txtId: TextView
    lateinit var txtNombreDoctor: TextView
    lateinit var txtFecha: TextView
    lateinit var spHora : Spinner
    lateinit var Hora : String
    lateinit var txtNombrePaciente: EditText
    lateinit var txtTelefono: EditText
    lateinit var db :consultorioplanner
    val Horas = arrayOf("8", "9","10","11","12","1","2")

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

        var id = intent.getStringExtra("idDoctor2")
        var id2 = id.toString()
        txtId.text = id

        var fechita= intent.getStringExtra("fecha")
        txtFecha.text = fechita

        val adaptador = ArrayAdapter(this ,android.R.layout.simple_spinner_item,Horas)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spHora.adapter = adaptador
        spHora.onItemSelectedListener = this


        lifecycleScope.launch{
            val doctor = db.room.DoctoresDao().getById(id2)
            txtNombreDoctor.text = doctor.nombre
        }

        txtFecha.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
            showDataPickerDialog();
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
        Log.e("SPINNER", "Se selecciono algo: $posicion")

        Hora = Horas[posicion]

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun showDataPickerDialog(){
        val newFragment = DatePickerDateFragment2.newInstance(
            DatePickerDialog
            .OnDateSetListener { _, year, month, day ->
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
                txtFecha.setText(selectedDate)
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


}