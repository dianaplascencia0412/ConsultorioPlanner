package mx.edu.tecmm.consultorioplanner

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MostrarCitas : AppCompatActivity() {
    lateinit var txtId: TextView
    lateinit var txtNombre: TextView
    lateinit var txtFecha:EditText
    lateinit var db :consultorioplanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_citas)
        txtId = findViewById(R.id.txtIdDoctor)
        txtNombre = findViewById(R.id.txtNombreDoctor)
        txtFecha = findViewById(R.id.edFechita)
       // txtFecha.setOnClickListener{showDatePickerDialog()}



        txtFecha.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
            showDataPickerDialog();
        }

        db = this.application as consultorioplanner

        val hoy = System.currentTimeMillis()
        val fecha = Date(hoy)
        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val salida: String = df.format(fecha)
        txtFecha.setText(salida)

        //val bundle: Bundle? = intent.extras
        //val string: String? = intent.getStringExtra("idDoctor")

        var id = intent.getStringExtra("idDoctor")
        var id2 = id.toString()



        lifecycleScope.launch{

                val doctor = db.room.DoctoresDao().getById(id2)
            txtId.text = id2
           txtNombre.text = doctor.nombre


        }




    }

   /* private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment {day, month, year -> onDateSelected(day, month+1, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }
    fun onDateSelected(day:Int, month:Int, year:Int){
        if (month<10){
        txtFecha.setText("$day/0$month/$year")
        } else if (day<10){
            txtFecha.setText("0$day/$month/$year")
        }else if (month<10 && day<10){
            txtFecha.setText("0$day/0$month/$year")
        }else{
            txtFecha.setText("$day/$month/$year")
            }
        }*/

    fun showDataPickerDialog(){
        val newFragment = DatePickerDateFragment2.newInstance(DatePickerDialog
            .OnDateSetListener { _, year, month, day ->
                val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
                txtFecha.setText(selectedDate)
            })
        newFragment.show(supportFragmentManager, "datePicker")


    }
    fun abrirAgregarCita(v: View){

        val intent= Intent(this,AgregarCita::class.java)
        var id = txtId.text
        intent.putExtra("idDoctor2", id)
        startActivity(intent)


    }



}