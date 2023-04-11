package com.example.carpoolapp

class Offers(
    var userName: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var address: String? = null
) {
    override fun toString(): String {
        return "Offers(userName=$userName, name=$name, email=$email, phoneNumber=$phoneNumber, address=$address)"
    }
}
