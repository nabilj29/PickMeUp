package com.example.carpoolapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestAdapter(private val requestList: ArrayList<User>, param: (Any) -> Unit) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {
    private val selectedUsers = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_request_item, parent, false)
        Log.d("RequestAdapter", "onCreateViewHolder")
        return RequestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val requestData: User = requestList[position]
        holder.userName.text = requestData.userName
        holder.address.text = requestData.address
        holder.rating.text = requestData.rating
        holder.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                selectedUsers.add(requestData)
                Log.d("RequestAdapter", "User ${requestData.userName} selected")
            } else {
                selectedUsers.remove(requestData)
                Log.d("RequestAdapter", "User ${requestData.userName} unselected")
            }

        }
        Log.d("RequestAdapter", "onBindViewHolder")
    }

    override fun getItemCount(): Int {
//        Log.d("RequestAdapter", "getItemCount: ${requestList.size}")
        return requestList.size
    }
    fun getSelectedUsers(): ArrayList<User> {
        return selectedUsers
    }

    public class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.requestingusernamefield)
        val address: TextView = itemView.findViewById(R.id.requeststartinglocationfield)
        val rating: TextView = itemView.findViewById(R.id.ratinguser_rating)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

        init {
            Log.d("RequestAdapter", "RequestViewHolder")
        }
    }
}
