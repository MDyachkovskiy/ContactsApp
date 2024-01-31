package com.test.application.core.domain

data class Location(
    val city: String = "",
    val country: String = "",
    val postcode: String = "",
    val longitude: Float = 0.0f,
    val latitude: Float = 0.0f,
    val state: String = "",
    val street: String = ""
)
