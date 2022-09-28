package mx.edu.tecmm.consultorioplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var datoslist : ArrayList<Persona>
    private lateinit var adaptadorPersona: AdaptadorPersona

    companion object{
      //  val personas =  ArrayList<Persona> ()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler= findViewById(R.id.rv_persona)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)

        datoslist = ArrayList()

        datoslist.add(Persona("Sara", "1"))
        datoslist.add(Persona("Diana", "2"))
        datoslist.add(Persona("Jose" ,"3"))


        adaptadorPersona= AdaptadorPersona(datoslist)
        recycler.adapter = adaptadorPersona


        adaptadorPersona.onItemClick = {
            val intent = Intent(this, MostrarCitas::class.java)
            intent.putExtra("", it)
            startActivity(intent)
        }


    }

   /* override fun onPostResume() {
        super.onPostResume()
        val adaptador = AdaptadorPersona(this, personas)
        recycler.adapter = adaptador
    }*/

    fun AgregarDoctor(v: View){
        val intent= Intent(this,AgregarDoctor::class.java)
        startActivity(intent)
    }




}