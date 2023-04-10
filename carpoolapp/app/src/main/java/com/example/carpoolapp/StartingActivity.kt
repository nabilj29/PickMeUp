package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carpoolapp.databinding.ActivityMainBinding
import androidx.fragment.app.FragmentTransaction
import android.view.View
import android.view.ViewGroup

class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        var mvtologin = findViewById<android.widget.Button>(R.id.movetologinbtn);
        var mvtosignup = findViewById<android.widget.Button>(R.id.movetosignupbtn);
        mvtologin.setOnClickListener{
            val intent = android.content.Intent(this, LoginActivity()::class.java)
            startActivity(intent)
        }
        mvtosignup.setOnClickListener{
            val intent = android.content.Intent(this, RegistrationActivity()::class.java)
            startActivity(intent)
        }
    }


    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}

