package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registration)

        var mvtologin = findViewById<android.widget.Button>(R.id.registerdetailsbtn);
        mvtologin.setOnClickListener{
            val intent = android.content.Intent(this, LoginActivity()::class.java)
            startActivity(intent)
        }

    }
}