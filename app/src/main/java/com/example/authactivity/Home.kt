package com.example.authactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        user_email.text =  intent.getStringExtra("email")
        user_id.text = intent.getStringExtra("id")

        logout.setOnClickListener {
            Firebase.auth.signOut()
            updateUI()
            Toast.makeText(baseContext, "signOut Success.",
                Toast.LENGTH_SHORT).show()
        }

    }
    private fun updateUI() {
        var i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


}