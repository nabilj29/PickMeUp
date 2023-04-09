package com.example.carpoolapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class OfferRideDetailsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_offer_ride_details, container, false)
        val submitofferbutton = v.findViewById<android.widget.Button>(R.id.submitOfferbutton)
        val offerRef = FirebaseDatabase.getInstance().getReference("Offer")

        submitofferbutton.setOnClickListener {
            val startingAddressEditText = v.findViewById<EditText>(R.id.enterStartingLocation)
            val startingAddress = startingAddressEditText.text.toString()
            val destinationAddressEditText = v.findViewById<EditText>(R.id.enterDestinationLocation)
            val destinationAddress = destinationAddressEditText.text.toString()
            val maxRidersEditText = v.findViewById<EditText>(R.id.enterMaxRiders)
            val maxRiders = maxRidersEditText.text.toString()

            val offerData = OfferDataClass(startingAddress, destinationAddress, maxRiders)

            offerRef.push().child(startingAddress).setValue(offerData)


            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.FragmentContainer, RequestDisplayingFragment())
            transaction.commit()
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OfferRideDetailsFragment().apply {

            }
    }
}