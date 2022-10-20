package mx.edu.tecmm.consultorioplanner

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class AdaptadorDoctor(context: Context, private  var datos: List<Doctores> ,private var doctor:cellClikListenerDoctor): RecyclerView.Adapter<AdaptadorDoctor.ViewHolderDoctor> () {
    class ViewHolderDoctor(item: View):
        RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNombre)
        var txtId: TextView = item.findViewById(R.id.txtId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDoctor {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent,false)
        return ViewHolderDoctor(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolderDoctor, position: Int) {
        val doctores = datos [position]
        holder.txtId.text = doctores.idDoctor
        holder.txtNombre.text = doctores.nombre
        holder.itemView.setOnClickListener{
            doctor.clickDoctor(doctores)
            Log.e("click", "di click")
        }

    }

    override fun getItemCount(): Int = datos.size



}