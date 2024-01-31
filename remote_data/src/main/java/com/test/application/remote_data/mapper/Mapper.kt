package com.test.application.remote_data.mapper

import com.test.application.core.domain.ContactAvatar
import com.test.application.core.domain.ContactInfo
import com.test.application.core.domain.Location
import com.test.application.remote_data.dto.ContactInfoDTO
import com.test.application.remote_data.dto.ResultDTO

fun ResultDTO.toContactInfo(): ContactInfo {
    return ContactInfo (
        cell = this.cell,
        phone = this.phone,
        birthday = this.dob.date,
        email = this.email,
        id = "${this.id.name} ${this.id.value}".trim(),
        location = Location(
            city = this.location.city,
            country = this.location.country,
            postcode = this.location.postcode,
            state = this.location.state,
            street = "${this.location.street.number} ${this.location.street.name}".trim()
        ),
        name = "${this.name.title} ${this.name.first} ${this.name.last}".trim(),
        picture = ContactAvatar(
            medium = this.picture.medium,
        )
    )
}

fun ContactInfoDTO.toContactInfoList(): List<ContactInfo> {
    return this.results.map { it.toContactInfo() }
}