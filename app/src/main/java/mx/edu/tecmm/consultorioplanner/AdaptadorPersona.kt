package mx.edu.tecmm.consultorioplanner

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersona(private val datos:ArrayList<Persona>)
: RecyclerView.Adapter<AdaptadorPersona. ViewHolderPersona>() {
    var onItemClick: ((Persona) -> Unit)? = null
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
        holder.txtNombre.text =  persona.Nombre
        holder.txtId.text = persona.Id

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(persona)
            val selectedPosition=0

            if(selectedPosition == position)
                holder.itemView.setBackgroundColor(Color.GRAY);

        }



       /* holder.itemView.setOnClickListener(new View.OnClickListener {
           @Override
           public void onClick(View V){

           }
        })*/

    }

    override fun getItemCount(): Int = datos.size


}