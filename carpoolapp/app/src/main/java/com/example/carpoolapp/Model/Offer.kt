package com.example.carpoolapp.Model

data class Offer (
    var startlocation: String,
    var destination: String,
    var max_passengers: Int,
    var time: String,
    var date: String,
    var taxiID: String,
    var offerer: User
        )