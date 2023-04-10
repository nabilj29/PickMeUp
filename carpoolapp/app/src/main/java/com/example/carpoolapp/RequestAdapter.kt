package com.example.carpoolapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestAdapter(private val requestList: ArrayList<User>) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_request_item, parent, false)
        Log.d("RequestAdapter", "onCreateViewHolder")
        return RequestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val requestData: User = requestList[position]
        holder.userName.text = requestData.userName
        holder.address.text = requestData.address
        Log.d("RequestAdapter", "onBindViewHolder")
    }

    override fun getItemCount(): Int {
        Log.d("RequestAdapter", "getItemCount: ${requestList.size}")
        return requestList.size
    }

    public class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.requestingusernamefield)
        val address: TextView = itemView.findViewById(R.id.requeststartinglocationfield)

        init {
            Log.d("RequestAdapter", "RequestViewHolder")
        }
    }
}
