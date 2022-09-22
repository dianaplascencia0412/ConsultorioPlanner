package mx.edu.tecmm.consultorioplanner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersona(context: Context, private  var datos: List<Persona>):
    RecyclerView.Adapter<AdaptadorPersona.ViewHolderPersona> () {
    class ViewHolderPersona(item: View):
        RecyclerView.ViewHolder(item){
        var txtNombre : TextView = item.findViewById(R.id.txtNombre)
        var txtId: TextView = item.findViewById(R.id.txtId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersona {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent,false)
        return ViewHolderPersona(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolderPersona, position: Int) {
        val persona = datos [position]
        holder.txtNombre.text =  persona.nombre
        holder.txtId.text = persona.id

       /* holder.itemView.setOnClickListener(new View.OnClickListener {
           @Override
           public void onClick(View V){

           }
        })*/

    }

    override fun getItemCount(): Int = datos.size


}