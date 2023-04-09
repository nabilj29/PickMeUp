package com.example.carpoolapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_offer_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val user : User = userList[position]
        holder.userName.text = user.userName
        holder.address.text = user.address
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userName : TextView = itemView.findViewById(R.id.offeringusernamefield)
        val address : TextView = itemView.findViewById(R.id.offerdestlocationfield)

    }

}