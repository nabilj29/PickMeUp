package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.carpoolapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.BottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.homeMenu -> replaceFragment(HomeFragment())
                R.id.requestRideMenu -> replaceFragment(RequestRideFragment())
                R.id.offerRideMenu -> replaceFragment(OfferRideFragment())
                R.id.settingsMenu -> replaceFragment(ProfileFragment())
                else ->{
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}