package mx.edu.tecmm.consultorioplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AgregarCita : AppCompatActivity() {
    lateinit var txtId: TextView
    lateinit var txtNombreDoctor: TextView
    lateinit var db :consultorioplanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cita)
        txtId = findViewById(R.id.txtIdDoctorAC)
        txtNombreDoctor = findViewById(R.id.txtNombreD)

        var id = intent.getStringExtra("idDoctor2")
        txtId.text = id
        //var id2 = id.toString()

        /*lifecycleScope.launch{

            val doctor = db.room.DoctoresDao().getById(id2)
            txtId.text = id
            txtNombreDoctor.text = doctor.nombre

        }*/
    }
}