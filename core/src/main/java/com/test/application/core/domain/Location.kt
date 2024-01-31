package com.test.application.core.domain

data class Location(
    val city: String = "",
    val country: String = "",
    val postcode: Int = 0,
    val state: String = "",
    val street: String = ""
)
