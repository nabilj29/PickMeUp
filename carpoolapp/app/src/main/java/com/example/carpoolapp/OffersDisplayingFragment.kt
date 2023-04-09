package com.example.carpoolapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class OffersDisplayingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_offers_displaying, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myAdapter = MyAdapter(userArrayList)
        recyclerView.adapter = myAdapter

        // initialize Firebase database reference
        db = Firebase.database.reference.child("User")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()

                for (offerSnapshot in snapshot.children) {
                    val offer = offerSnapshot.getValue(User::class.java)
                    userArrayList.add(offer!!)
                }

                myAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error fetching offers", error.toException())
            }
        })

        return rootView
    }

    companion object {
        private const val TAG = "OffersDisplayingFragment"

        @JvmStatic
        fun newInstance() = OffersDisplayingFragment()
    }
}
