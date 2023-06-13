package com.example.splash_login_recyclerview
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class MainActivityImagenRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_imagen_recycler_view)

        var photoView = findViewById<PhotoView>(R.id.photo_view)
        var colortrajee = findViewById<TextView>(R.id.ct)
        val strValor1 = intent.getStringExtra("colortra")
        var razza = findViewById<TextView>(R.id.razaa)
        val strValor2 = intent.getStringExtra("raz")
        var univers = findViewById<TextView>(R.id.univ)
        val strValor3 = intent.getStringExtra("uni")
        if(intent.extras != null)
        {
            Glide.with(this)
                .load(intent.getStringExtra("imageUrl"))
                .into(photoView)

            colortrajee.text = "Color traje:"+strValor1
            razza.text = "Raza:"+strValor2
            univers.text = "Universo:"+strValor3
        }
    }
}