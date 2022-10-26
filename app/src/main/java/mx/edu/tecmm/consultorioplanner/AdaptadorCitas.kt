package mx.edu.tecmm.consultorioplanner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCitas (context: Context, private  var datos: List<Doctores>): RecyclerView.Adapter<AdaptadorCitas.ViewHolderCitas> () {
    class ViewHolderCitas(item: View):
        RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNombreP)
        var txtFecha: TextView = item.findViewById(R.id.txtFechaC)
        var txtHora: TextView = item.findViewById(R.id.txtHoraC)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitas {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent,false)
        return ViewHolderCitas(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolderCitas, position: Int) {
        val doctores = datos [position]

        holder.txtNombre.text = doctores.nombre
        holder.txtFecha.text = doctores.idDoctor
        holder.txtHora.text = doctores.nombre
    }

    override fun getItemCount(): Int = datos.size



}