package com.test.application.remote_data.dto

data class Result(
    val cell: String = "",
    val dob: Dob = Dob(),
    val email: String = "",
    val phone: String = "",
    val id: Id = Id(),
    val location: Location = Location(),
    val name: Name = Name(),
    val picture: Picture = Picture(),
)