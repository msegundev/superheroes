package com.example.splash_login_recyclerview
import android.content.Intent
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

public class MainActivity2Todo : AppCompatActivity(),ElementoAdapterRecyclerTodo.onItemListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Splash_login_recyclerview)
        setContentView(R.layout.activity_main_activity2_todo)
        setupRecyclerView1()
    }

    private fun setupRecyclerView1() {
        val db = Firebase.firestore
        val personajes = db.collection("personajes")
        var listaPersonajes: MutableList<Personaje> = mutableListOf()

        db.collection("personajes")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var personajeItem = document.toObject<Personaje>()
                    listaPersonajes.add(personajeItem)

                }

                var recycler = findViewById<RecyclerView>(R.id.rvPersonajes)



                recycler.adapter = ElementoAdapterRecyclerTodo(this,listaPersonajes, this)
                recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


    }

    override fun onImagenItinerarioClick(image: String,ColorTraje:String,Raza:String,Universo:String) {

        val intent = Intent(this, MainActivityImagenRecyclerView::class.java)
        intent.putExtra("imageUrl", image)
        intent.putExtra("colortra", ColorTraje)
        intent.putExtra("raz", Raza)
        intent.putExtra("uni", Universo)
        startActivity(intent)

    }


    override fun onItemClick(IdPersonaje: String) {
        Toast.makeText(this, "Funciona el nuevo Toast", Toast.LENGTH_LONG).show()
    }
    override fun onIconClick(icon: String) {
        if(icon.equals("https://firebasestorage.googleapis.com/v0/b/proyecto2022multimedia2.appspot.com/o/Iconos%2FMarvel.png?alt=media&token=ad7f2afe-9684-4a0b-af30-a5ad3dc9b3c1")){
            val intent = Intent(this, MainActivity4SoloMarvel::class.java)
            startActivity(intent)
        }
        else if(icon.equals("https://firebasestorage.googleapis.com/v0/b/proyecto2022multimedia2.appspot.com/o/Iconos%2Fdc.png?alt=media&token=b06a57f7-c60e-4376-a272-be95e3c26ae1")){
            val intent = Intent(this, MainActivity3SoloDc::class.java)
            startActivity(intent)
        }
    }

}
