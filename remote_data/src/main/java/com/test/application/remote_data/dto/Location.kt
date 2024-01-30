package com.test.application.remote_data.dto

data class Location(
    val city: String = "",
    val country: String = "",
    val postcode: Int = 0,
    val state: String = "",
    val street: Street = Street()
)