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
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MostrarCitas : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var txtId: TextView
    lateinit var txtNombre: TextView
    lateinit var txtFecha:EditText
    lateinit var db :consultorioplanner
    lateinit var salida: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_citas)
        txtId = findViewById(R.id.txtIdDoctor)
        txtNombre = findViewById(R.id.txtNombreDoctor)
        txtFecha = findViewById(R.id.edFechita)
        recycler = findViewById(R.id.rv_cita)
       // txtFecha.setOnClickListener{showDatePickerDialog()}
        db = this.application as consultorioplanner

        txtFecha.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(txtFecha.windowToken, 0)
            showDataPickerDialog();
        }
        val hoy = System.currentTimeMillis()
        val fecha = Date(hoy)
        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        salida = df.format(fecha)
        txtFecha.setText(salida)



        var id = intent.getStringExtra("idDoctor")
        var id2 = id.toString()

        lifecycleScope.launch{

                val doctor = db.room.DoctoresDao().getById(id2)
            txtId.text = id2
           txtNombre.text = doctor.nombre


        }
    }

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
        intent.putExtra("fecha", salida)
        startActivity(intent)
    }

   override fun onPostResume() {
        super.onPostResume()
        actualizarRecycler()
    }
    fun actualizarRecycler(){
        lifecycleScope.launch{
            val lista= db.room.DoctoresDao().getAll()
            actualizarRecyclerDespues(lista)
        }
    }
    fun actualizarRecyclerDespues(list: List<Doctores>){
        val adaptador = AdaptadorCitas(this, list)
        recycler.adapter = adaptador
    }
}