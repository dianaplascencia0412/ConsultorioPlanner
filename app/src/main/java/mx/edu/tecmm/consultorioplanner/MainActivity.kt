package mx.edu.tecmm.consultorioplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView

    companion object{
        val personas =  ArrayList<Persona> ()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.rv_persona)
        personas.add(Persona("Diana" , "19852220"))
        personas.add(Persona("Sara" , "4548845"))
        personas.add(Persona("Jose" , "4545492"))
        val adaptador = AdaptadorPersona(this, personas)
        recycler.adapter = adaptador
    }

    override fun onPostResume() {
        super.onPostResume()
        val adaptador = AdaptadorPersona(this, personas)
        recycler.adapter = adaptador
    }

    fun AgregarDoctor(v: View){
        val intent= Intent(this,AgregarDoctor::class.java)
        startActivity(intent)
    }


}