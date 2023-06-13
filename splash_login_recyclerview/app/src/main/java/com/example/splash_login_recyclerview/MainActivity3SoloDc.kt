package com.example.splash_login_recyclerview

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

public class MainActivity3SoloDc : AppCompatActivity(),ElementoAdapterRecyclerSoloDc.onItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Splash_login_recyclerview)
        setContentView(R.layout.activity_main_activity3_solo_dc)
        setupRecyclerView3()
    }

private fun setupRecyclerView3() {
    val db = Firebase.firestore
    val personajes = db.collection("personajes")
    var listaPersonajesdc: MutableList<Personaje> = mutableListOf()

    db.collection("personajes")
        .whereEqualTo("Universo","Dc comics")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                var personajeItem = document.toObject<Personaje>()
                listaPersonajesdc.add(personajeItem)

            }

            var recycler = findViewById<RecyclerView>(R.id.rvPersonajesdc)



            recycler.adapter = ElementoAdapterRecyclerSoloDc(this,listaPersonajesdc, this)
            recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
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
