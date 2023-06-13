package com.example.splash_login_recyclerview

import android.os.Bundle
import android.content.Intent
import android.content.ContentValues.TAG

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

public class MainActivity4SoloMarvel : AppCompatActivity(),ElementoAdapterRecyclerSoloMarvel.onItemListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Splash_login_recyclerview)
        setContentView(R.layout.activity_main_activity4_solo_marvel)
        setupRecyclerView2()
    }

    private fun setupRecyclerView2() {
        val db = Firebase.firestore
        val personajes = db.collection("personajes")
        var listaPersonajesmarvel: MutableList<Personaje> = mutableListOf()

        db.collection("personajes")
            .whereEqualTo("Universo","Marvel comics")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var personajeItem = document.toObject<Personaje>()
                    listaPersonajesmarvel.add(personajeItem)

                }

                var recycler = findViewById<RecyclerView>(R.id.rvPersonajesmarvel)



                recycler.adapter = ElementoAdapterRecyclerSoloMarvel(this,listaPersonajesmarvel, this)
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

}
