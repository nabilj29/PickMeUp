package com.example.carpoolapp
import java.math.RoundingMode
import java.text.DecimalFormat


// make sure distance is in km and time is in minutes

fun calculateFare(distance: Double, time: Double, num_riders: Double) : String{
    val final_fare = (3.0 + (1.3 * distance + 0.10 * time))/num_riders
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.UP
    val fare_rounded = df.format(final_fare).toString()
    return fare_rounded

}

