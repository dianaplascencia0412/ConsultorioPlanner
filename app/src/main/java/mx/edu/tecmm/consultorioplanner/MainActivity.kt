package mx.edu.tecmm.consultorioplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() , cellClikListenerDoctor {
   lateinit var recycler: RecyclerView
    lateinit var db :consultorioplanner

   /* companion object{
        //val doctores =  ArrayList<Doctores> ()
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.rv_personas)

        db = this.application as consultorioplanner
    }


    fun abrirAgregarDoctor(v: View){
        val intent= Intent(this,AgregarDoctor::class.java)
        startActivity(intent)
    }
    fun abrirMostrarCitas(v: View){
        val intent = Intent(this, MostrarCitas::class.java)
        intent.putExtra("idDoctor","1")
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
        val adaptador = AdaptadorDoctor(this, list , this)
        recycler.adapter = adaptador
    }

    override fun clickDoctor(doctor: Doctores) {
        val intent = Intent(this , MostrarCitas::class.java)
        intent.putExtra("idDoctor", doctor.idDoctor)
        startActivity(intent)
        Log.e("id", "${doctor.idDoctor}")
    }


}