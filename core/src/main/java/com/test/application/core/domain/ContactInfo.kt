package com.test.application.core.domain



data class ContactInfo(
    val cell: String = "",
    val birthday: String = "",
    val email: String = "",
    val id: String = "",
    val location: String = "",
    val name: String = "",
    val picture: ContactAvatar = ContactAvatar(),
)
