package com.example.authactivity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI(currentUser)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val email = userEmil.text
        val password = userPassword.text


        btn_login.setOnClickListener {
            if(email.isNotEmpty() && password.isNotEmpty()){
                signInWithEmailAndPassword(email.toString(),password.toString())
                Toast.makeText(baseContext, "LogIn Success.",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(baseContext, "LogIn Failed. Please enter the EMPTY Fields .",
                    Toast.LENGTH_SHORT).show()
            }
        }
        gotoSignup.setOnClickListener {
            val i = Intent(this, signup::class.java)
            startActivity(i)
        }
    }
    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Asmaa:signInWithEmail", "signInWithEmail:success")
                    Toast.makeText(baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Asmaa:signInWithEmail", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {
        var i = Intent(this, Home::class.java)
        i.putExtra("email",user!!.email)
        i.putExtra("id",user.uid)
        startActivity(i)
    }


}