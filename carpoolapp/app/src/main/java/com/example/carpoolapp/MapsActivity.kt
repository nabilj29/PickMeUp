package com.example.carpoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject
import android.graphics.Color
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.carpoolapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.LatLngBounds

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        var finishride  = findViewById<android.widget.Button>(R.id.finishrideBtn);
        finishride.setOnClickListener{
            val intent = android.content.Intent(this@MapsActivity, RatingFormActivity()::class.java)
            startActivity(intent)
        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // Define the list of destinations
        val destinations = listOf(
            LatLng(10.3181466, 123.9029382), // Ayala
            LatLng(10.311795, 123.915864), // SM City
            LatLng(10.305961, 123.893612) // Cebu IT Park
        )

        // Add markers for all destinations
        destinations.forEachIndexed { index, latLng ->
            this.googleMap!!.addMarker(
                MarkerOptions().position(latLng).title("Destination ${index + 1}")
            )
        }

        // Set the bounds for the map camera
        val builder = LatLngBounds.Builder()
        destinations.forEach { builder.include(it) }
        val padding = 200 // in pixels
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), padding)

        // Add listener to wait for the layout to occur before setting the camera position and zoom level
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.view?.viewTreeObserver?.addOnGlobalLayoutListener {
            this.googleMap!!.moveCamera(cameraUpdate)
        }
        // Get the routes between all pairs of destinations
        val path: MutableList<List<LatLng>> = ArrayList()
        var totalDistance = 0.0
        var totalTime = 0
        var completedRequests = 0
        val apiKey = "AIzaSyA7JbQ-UI_ozF74Z7XBMrYWzGHGors8MT4"
        for (i in 0 until destinations.size - 1) {
            val origin = destinations[i]
            val destination = destinations[i + 1]
            val urlDirections =
                "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${destination.latitude},${destination.longitude}&key=$apiKey"

            val directionsRequest = object : StringRequest(
                Request.Method.GET,
                urlDirections,
                Response.Listener<String> { response ->
                    val jsonResponse = JSONObject(response)
                    // Get routes
                    val routes = jsonResponse.getJSONArray("routes")
                    val legs = routes.getJSONObject(0).getJSONArray("legs")
                    val steps = legs.getJSONObject(0).getJSONArray("steps")
                    for (i in 0 until steps.length()) {
                        val points =
                            steps.getJSONObject(i).getJSONObject("polyline").getString("points")
                        path.add(PolyUtil.decode(points))
                        totalDistance += steps.getJSONObject(i).getJSONObject("distance")
                            .getDouble("value")
                        totalTime += steps.getJSONObject(i).getJSONObject("duration")
                            .getInt("value")
                    }
                    for (i in 0 until path.size) {
                        this.googleMap!!.addPolyline(
                            PolylineOptions().addAll(path[i]).color(Color.RED)
                        )
                    }
                    // Increment completed requests counter
                    completedRequests++
                    // If all requests are completed, print the total distance and time
                    if (completedRequests == destinations.size - 1) {
                        val distanceInKm = totalDistance / 1000.0
                        val timeInMin = totalTime/60.0
                        Log.d("Distance", "Total distance: $distanceInKm km")
                        Log.d("Duration", "Total duration: $timeInMin min")
                    }
                },
                Response.ErrorListener {
                    // Handle error
                }) {}
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(directionsRequest)
        }



    }
    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}