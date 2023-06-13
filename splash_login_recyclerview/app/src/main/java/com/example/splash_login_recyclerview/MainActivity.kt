package com.example.splash_login_recyclerview
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imagen = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.batman).into(imagen)



        var user = findViewById<TextView>(R.id.editTextTextPersonName2)
        user.setBackgroundColor(Color.parseColor("#f8f32b"))//añadido para cambiar color boton
        user.setTextColor(Color.BLACK)
        var password = findViewById<TextView>(R.id.editTextTextPersonName3)
        password.setBackgroundColor(Color.parseColor("#f8f32b"))//añadido para cambiar color boton
        password.setTextColor(Color.BLACK)

        var botonRegistro = findViewById<Button>(R.id.button2)
        botonRegistro.setBackgroundColor(Color.parseColor("#f8f32b"))//añadido para cambiar color boton
        botonRegistro.setTextColor(Color.BLACK)
        var botonlogin = findViewById<Button>(R.id.button)
        botonlogin.setBackgroundColor(Color.parseColor("#f8f32b"))//añadido para cambiar color boton
        botonlogin.setTextColor(Color.BLACK)
        botonlogin.setOnClickListener{
            if (user.text.isNotEmpty() && password.text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    user.text.toString().trim(),
                    password.text.toString().trim()
                ).addOnCompleteListener {

                    if (it?.isSuccessful == true) {
                        var toast1 = Toast.makeText(this, "Login Correcto",
                            Toast.LENGTH_LONG)
                        toast1.setGravity(Gravity.LEFT,0,100)
                        toast1.show()
                        startActivity(Intent(this, MainActivity2Todo::class.java))
                        //Enviamos información del usuario actual a la siguiente Activity.
                        //Esta información se va a ir pasando de la misma manera por las activities
                        //por las que vayamos pasando





                    } else {
                        Toast.makeText(this, "Login Incorrecto", Toast.LENGTH_LONG).show()
                    }

                }

            }
        }

        botonRegistro.setOnClickListener {

            val providerAuth = "user y password"
            if (user.text.isNotEmpty() && password.text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    user.text.toString().trim(),
                    password.text.toString().trim()
                ).addOnCompleteListener {

                    if (it?.isSuccessful == true) {


                        Toast.makeText(this, "Registro Correcto", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java).apply {
                            putExtra("user", user.text.toString())
                            putExtra("password", password.text.toString())
                        }
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Registro Incorrecto", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }



    }

}