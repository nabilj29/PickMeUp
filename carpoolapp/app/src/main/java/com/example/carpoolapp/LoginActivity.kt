package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var FBauth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        FBauth = FirebaseAuth.getInstance()
        var logb = findViewById<Button>(R.id.loginbtn);
        var logUser = findViewById<android.widget.TextView>(R.id.loginusername);
        var logPass = findViewById<android.widget.TextView>(R.id.loginpassword);
        logb.setOnClickListener {
            val user = logUser.text.toString()
            val pass = logPass.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                FBauth.signInWithEmailAndPassword(user, pass).addOnCompleteListener{
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


//        var mvtohome = findViewById<android.widget.Button>(R.id.loginbtn);
//        mvtohome.setOnClickListener{
//            val intent = android.content.Intent(this, MainActivity()::class.java)
//            startActivity(intent)
//        }
    }
}