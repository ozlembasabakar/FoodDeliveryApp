package com.example.fooddelivery.data

data class CustomerInfo(
    var customerInfoList: ArrayList<CloudCustomerItem> = arrayListOf()
)

data class CloudCustomerItem(
    var id: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var email: String? = null,
    var password: String? = null,
    var phoneNumber: String? = null,
    )

