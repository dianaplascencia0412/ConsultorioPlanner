package mx.edu.tecmm.consultorioplanner

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AgregarCita : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    lateinit var txtId: TextView
    lateinit var txtNombreDoctor: TextView
    lateinit var txtFecha: TextView
    lateinit var spHora : Spinner
    lateinit var Hora : String
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
        //txtTitulo.text = lenguaje
       /* txtDescripcion.text = when (posicion) {
            0 -> "Java es un lenguaje sencillo y orientado a objetos, que permite el desarrollo de aplicaciones "
            1-> "PHP (Hypertext Preprocessor ) es un lenguaje de código abierto muy popular especialmente adecuado para el desarrollo web y que puede ser incrustado en HTML"
            2 -> "C# es un lenguaje de programación multiparadigma desarrollado y estandarizado por la empresa Microsoft como parte de su plataforma .NET"
            3 ->  "Un lenguaje de programación dinámico y de código abierto , se utiliza principalmente en el desarrollo de aplicaciones web"
            4 -> "Swift es un lenguaje de programación multiparadigma creado por Apple enfocado en el desarrollo de aplicaciones para iOS y macOS"
            else -> ""
        }*/


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


}