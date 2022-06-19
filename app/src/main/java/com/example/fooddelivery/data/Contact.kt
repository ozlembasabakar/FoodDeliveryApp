package com.example.fooddelivery.data

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

class Contact(
    var name: String? = null,
    var surname: String? = null,
    var email: String? = null,
    var password: String? = null,
    var phoneNumber: String? = null
) {
}
