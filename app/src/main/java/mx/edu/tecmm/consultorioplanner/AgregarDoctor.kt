package mx.edu.tecmm.consultorioplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AgregarDoctor : AppCompatActivity() {
    lateinit var txtNombre: EditText
    lateinit var txtId: EditText
    lateinit var db :consultorioplanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_doctor)
        txtId = findViewById(R.id.edId)
        txtNombre= findViewById(R.id.edNombre)

        db = this.application as consultorioplanner
        supportActionBar?.hide()

    }
    fun agregarDoctor(v: View) {

        //Aqui agrego un doctor a la base de datos
        val id= txtId.text.toString()
        val nombre = txtNombre.text.toString()
        if(id.equals("") or nombre.equals("")){
            Toast.makeText(this, "Error complete todos los datos", Toast.LENGTH_LONG).show()
        }
        else {
            lifecycleScope.launch {
                val doctor = Doctores(id, nombre)
                db.room.DoctoresDao().insert(doctor)
            }
            Toast.makeText(this, "Doctor Registrado", Toast.LENGTH_LONG).show()
            finish()

        }
    }
}