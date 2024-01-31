package com.test.application.core.domain



data class ContactInfo(
    val cell: String = "",
    val phone: String = "",
    val birthday: String = "",
    val email: String = "",
    val id: String = "",
    val location: Location = Location(),
    val name: String = "",
    val picture: ContactAvatar = ContactAvatar(),
)
