package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Locations>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var address: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
            R.drawable.mcmaster,
            R.drawable.art,
            R.drawable.golf,
            R.drawable.historic,
            R.drawable.hospital
        )

        heading = arrayOf(
            "McMaster University",
            "Art Gallery of Hamilton",
            "Chedoke Golf Club",
            "Dundurn Castle",
            "Hamilton General Hospital"
        )

        address = arrayOf(
            "1280 Main St W, Hamilton, ON",
            "123 King St W, Hamilton ON",
            "563 Aberdeen Ave, Hamilton, ON",
            "610 York Blvd, Hamilton, ON",
            "237 Barton St E, Hamilton, ON"
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Locations>()
        getUserdata()
    }

    private fun getUserdata() {
        for(i in imageId.indices){
            val location = Locations(imageId[i], heading[i], address[i])
            newArrayList.add(location)
        }

        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}