package com.example.authactivity

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
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
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
        setContentView(R.layout.activity_signup)
        auth = Firebase.auth
        val email = mail.text
        val password = password.text
        val confirmPassword = confrimpassword.text
        btn_signup.setOnClickListener {
            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password.toString().equals(confirmPassword.toString())) {
                    Log.e("Asmaa email", email.toString())
                    Log.e("Asmaa password", password.toString())
                    Log.e("Asmaa confirm password", confirmPassword.toString())
                    createNewAccount(email.toString(), password.toString())
                }
                else{
                    Toast.makeText(baseContext, "Password not Match.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(baseContext, "Register Failed. Please enter the EMPTY Fields .",
                    Toast.LENGTH_SHORT).show()
            }

        }
        login.setOnClickListener {
            val i = Intent(this, signup::class.java)
            startActivity(i)
        }
    }

    private fun createNewAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Asmaa:createUser", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    Toast.makeText(baseContext, "Authentication Success.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Asmaa:createUser", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

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