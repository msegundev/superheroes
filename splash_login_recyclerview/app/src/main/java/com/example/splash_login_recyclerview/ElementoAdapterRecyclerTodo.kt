package com.example.splash_login_recyclerview
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ElementoAdapterRecyclerTodo(
    var context: Context,
    var personajes: List<Personaje>,
    private val itemClickListener: onItemListener
    ): RecyclerView.Adapter<ElementoAdapterRecyclerTodo.ViewHolder>()
    {

        interface onItemListener {
            fun onImagenItinerarioClick(image: String,ColorTraje:String,Raza:String,Universo:String)
            fun onItemClick(albumId: String)
            fun onIconClick(icon: String)
        }

        inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            var idPersonaje = view.findViewById<TextView>(R.id.idpersonaje)
            var nombresuper = view.findViewById<TextView>(R.id.nombrepersonaje)
            var nombre = view.findViewById<TextView>(R.id.nombre)
            var apellido = view.findViewById<TextView>(R.id.apellido)
            var poder = view.findViewById<TextView>(R.id.poder)
            var imagenPersonaje = view.findViewById<ImageView>(R.id.idfoto)
            var iconoUniverso = view.findViewById<ImageView>(R.id.idIcon)

            fun bind(personaje: Personaje, context: Context) {
                idPersonaje.text = personaje.idPersonaje
                nombresuper.text = personaje.NombreSuper
                nombre.text = "Nombre:"+personaje.Nombre
                apellido.text = "Apellido:"+personaje.Apellido
                poder.text = "Poder:"+personaje.Poder
                Log.i("IMAGEN", "Imagen: ${personaje.image}")
                Log.i("ICONO", "Icono: ${personaje.icon}")
                Glide.with(context).load(personaje.image).into(imagenPersonaje);
                Glide.with(context).load(personaje.icon).into(iconoUniverso);
                view.setOnClickListener {
                    itemClickListener.onItemClick(personaje.NombreSuper)
                    Toast.makeText(view.context, "Elemento de listado pulsado", Toast.LENGTH_LONG)
                        .show()
                }
                imagenPersonaje.setOnClickListener {
                    itemClickListener.onImagenItinerarioClick(personaje.image, personaje.ColorTraje,personaje.Raza,personaje.Universo)
                }
                iconoUniverso.setOnClickListener{
                    itemClickListener.onIconClick(personaje.icon)
                }



            }


        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            //Aqui se infla la vista xml con datos

            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layoutpersonajes, parent, false)
            return ViewHolder(view)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            var personaje = personajes[position]
            holder.bind(personaje, context)
        }

        override fun getItemCount(): Int {
            return personajes.size
        }


    }