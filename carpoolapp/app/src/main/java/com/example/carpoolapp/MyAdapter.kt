package com.example.carpoolapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.carpoolapp.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment

class MyAdapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_offer_item, parent, false)
        val v = MyViewHolder(itemView)
        Log.d("MyAdapter", "onCreateViewHolder")


        return v
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val user : User = userList[position]
        holder.userName.text = user.userName
        holder.address.text = user.address
        holder.rating.text = user.rating
        Log.d("MyAdapter", "onBindViewHolder")
//        holder.reqridebtn.setOnClickListener{
//
//
//
//        }


    }

    override fun getItemCount(): Int {
        Log.d("MyAdapter", "getItemCount: ${userList.size}")

        return userList.size
    }

    public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userName : TextView = itemView.findViewById(R.id.offeringusernamefield)
        val address : TextView = itemView.findViewById(R.id.offerstartinglocationfield)
        val rating : TextView = itemView.findViewById(R.id.offererratingfield)
        var reqridebtn = itemView.findViewById<android.widget.Button>(R.id.requestridejoinButton)
        init {
            Log.d("MyAdapter", "MyViewHolder")
        }

    }

}