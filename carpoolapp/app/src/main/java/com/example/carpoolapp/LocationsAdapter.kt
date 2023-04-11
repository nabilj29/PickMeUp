package com.example.carpoolapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class LocationsAdapter(private val locationList: ArrayList<Locations>): RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsAdapter.LocationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_location_item, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentItem = locationList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.locationHeading.text = currentItem.heading
        holder.address.text = currentItem.address
    }

    class LocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val locationHeading: TextView = itemView.findViewById(R.id.locationHeading)
        val address: TextView = itemView.findViewById(R.id.address)

    }
}