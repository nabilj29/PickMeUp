package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        var mvtohome = findViewById<android.widget.Button>(R.id.loginbtn);
        mvtohome.setOnClickListener{
            val intent = android.content.Intent(this, MainActivity()::class.java)
            startActivity(intent)
        }
    }
}