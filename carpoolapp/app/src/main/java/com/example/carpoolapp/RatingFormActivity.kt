package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.roundToInt
import kotlin.random.Random

class RatingFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_rating_form)
        val fareValueTextView = findViewById<TextView>(R.id.fareValue)
        val fare = (Random.nextInt(1, 11))*1.13*2
        val rounded = (fare * 100).roundToInt() / 100.0
        val newFareValue = rounded.toString() // replace with your new fare value
        fareValueTextView.text = "$ $newFareValue"

        var mvtohome = findViewById<android.widget.Button>(R.id.confirmratingButton);
        mvtohome.setOnClickListener{
            val intent = android.content.Intent(this, MainActivity()::class.java)
            startActivity(intent)
        }
    }


}