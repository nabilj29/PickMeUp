package com.example.carpoolapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.carpoolapp.databinding.FragmentOfferRideBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OfferRideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OfferRideFragment : Fragment() {
    private lateinit var db: DatabaseReference
    private var _binding: FragmentOfferRideBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfferRideBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.submitIDbutton.setOnClickListener {
            val taxiId = binding.enterTaxiID.text.toString()
            Log.d("OfferRideFragment", "Taxi ID entered: $taxiId")


            db = Firebase.database.reference.child("Taxis")
            db.child(taxiId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Taxi with the given id exists, so transition to the new page
                        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaction.replace(R.id.FragmentContainer, OfferRideDetailsFragment())
                        transaction.commit()
                    } else {
                        // Taxi with the given id does not exist, show error message to user
                        Toast.makeText(requireContext(), "Taxi with the given ID does not exist", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(TAG, "Error checking if taxi with id $taxiId exists", error.toException())
                }
            })
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OfferRide.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OfferRideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}