package com.example.carpoolapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [FinalTripDisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FinalTripDisplayFragment : Fragment() {

    private lateinit var selectedUsers: ArrayList<String>
    private lateinit var database: FirebaseDatabase
    private lateinit var userRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedUsers = it.getStringArrayList("selectedUsers") ?: ArrayList<String>()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_final_trip_display, container, false)
        val finishRideBtn = view.findViewById<Button>(R.id.finishrideBtn)
        userRef = Firebase.database.reference.child("Users")
        Log.d(TAG, "Selected Users $selectedUsers")
        finishRideBtn.setOnClickListener {

            Log.d(TAG, "Selected Users $selectedUsers")
            val addresses = ArrayList<String>()
            for (userId in selectedUsers) {
                userRef.child(userId).child("address").addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val address = snapshot.getValue(String::class.java)
                        address?.let {
                            addresses.add(address)
                        }
                        if (addresses.size == selectedUsers.size) {
                            val geocoder = Geocoder(requireContext())
                            val locations = ArrayList<LatLng>()
                            for (addr in addresses) {
                                val results = geocoder.getFromLocationName(addr, 1)
                                if (results != null) {
                                    if (results.isNotEmpty()) {
                                        val location =
                                            results[0]?.let { it1 -> LatLng(it1.latitude, results[0].longitude) }
                                        if (location != null) {
                                            locations.add(location)
                                        }
                                    }
                                }
                            }
                            val intent = Intent(requireContext(), MapsActivity::class.java)
                            Log.d(TAG, "Selected Users $selectedUsers")
                            intent.putParcelableArrayListExtra("selectedLocations", locations)
                            startActivity(intent)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e(TAG, "Error retrieving address for user $userId: $error")
                    }
                })
            }
        }

        // Log the selected users
//        for (user in selectedUsers) {
//            Log.d(TAG, "Selected user: $user")
//        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(selectedUsers: ArrayList<String>) =
            FinalTripDisplayFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("selectedUsers", selectedUsers)
                }
            }
    }
}

