package com.example.carpoolapp.Model

data class Request (
    var requester: User,
    var startlocation: String,
    var destination: String
    // potential changes to trip duration also depends on offer
        )