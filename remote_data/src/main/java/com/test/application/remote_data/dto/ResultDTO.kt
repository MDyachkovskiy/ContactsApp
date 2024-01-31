package com.test.application.remote_data.dto

data class ResultDTO(
    val cell: String = "",
    val dob: DobDTO = DobDTO(),
    val email: String = "",
    val phone: String = "",
    val id: IdDTO = IdDTO(),
    val location: LocationDTO = LocationDTO(),
    val name: NameDTO = NameDTO(),
    val picture: PictureDTO = PictureDTO(),
)