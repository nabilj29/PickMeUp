package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RatingFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rating_form)

        var mvtohome = findViewById<android.widget.Button>(R.id.confirmratingButton);
        mvtohome.setOnClickListener{
            val intent = android.content.Intent(this, MainActivity()::class.java)
            startActivity(intent)
        }
    }


}