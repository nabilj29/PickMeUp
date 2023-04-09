package com.example.carpoolapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.carpoolapp.databinding.FragmentProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.saveProfilebtn.setOnClickListener {
            val userName = binding.userName.text.toString()
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            val address = binding.address.text.toString()



            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(userName, name, email, phoneNumber, address)
            database.child(userName).setValue(user).addOnSuccessListener {

                binding.userName.text.clear()
                binding.name.text.clear()
                binding.email.text.clear()
                binding.phoneNumber.text.clear()
                binding.address.text.clear()

                Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
