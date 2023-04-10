package com.example.carpoolapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [OffersDisplayingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestDisplayingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var requestArrayList: ArrayList<User>
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_request_displaying, container, false)
        Log.d(TAG, "Fragment view inflated successfully")
        recyclerView = rootView.findViewById(R.id.requestrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        requestArrayList = arrayListOf()
        requestAdapter = RequestAdapter(requestArrayList)
        recyclerView.adapter = requestAdapter
        Log.d(TAG, "Adapter set on RecyclerView")

        // initialize Firebase database reference
        db = Firebase.database.reference.child("Users")
        Log.d(TAG, "Connecting to Firebase database...")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                requestArrayList.clear()

                for (requestSnapshot in snapshot.children) {
                    val request = requestSnapshot.getValue(User::class.java)
                    requestArrayList.add(request!!)
                }

                requestAdapter.notifyDataSetChanged()
                Log.d(TAG, "Requests retrieved successfully")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error fetching requests", error.toException())
            }
        })

// CHECK IF THIS TRANSITION WORKS !!!!
        var confirmridebtn = rootView.findViewById<android.widget.Button>(R.id.confirmrideButton)
        confirmridebtn.setOnClickListener{
            val transaction: FragmentTransaction = getParentFragmentManager().beginTransaction()
            transaction.replace(R.id.FragmentContainer, FinalTripDisplayFragment())
            transaction.commit()
        }

        return rootView
    }

    companion object {
        private const val TAG = "OffersDisplayingFragment"

        @JvmStatic
        fun newInstance() = OffersDisplayingFragment()
    }
}
