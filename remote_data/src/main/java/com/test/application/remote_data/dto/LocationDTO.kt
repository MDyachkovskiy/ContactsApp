package com.test.application.remote_data.dto

data class LocationDTO(
    val city: String = "",
    val country: String = "",
    val postcode: Int = 0,
    val state: String = "",
    val street: StreetDTO = StreetDTO()
)