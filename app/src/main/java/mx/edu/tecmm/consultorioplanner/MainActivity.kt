package mx.edu.tecmm.consultorioplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
   lateinit var recycler: RecyclerView

    companion object{
        val doctores =  ArrayList<Doctores> ()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.rv_persona)
        doctores.add(Doctores("123456" , "Diana Rios"))
        doctores.add(Doctores("234567" , "Sara Beltran"))
        doctores.add(Doctores("345678" , "Jose Avila"))



        val adaptador = AdaptadorDoctor(this, doctores)
        recycler.adapter = adaptador
    }

    override fun onPostResume() {
        super.onPostResume()

    }

    fun AgregarDoctor(v: View){
        val intent= Intent(this,AgregarDoctor::class.java)
        startActivity(intent)
    }
    fun abrirMostrar(v: View){
        val intent = Intent(this, MostrarCitas::class.java)
        startActivity(intent)
    }




}