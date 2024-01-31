package com.test.application.local_data.mapper

import com.test.application.core.domain.ContactAvatar
import com.test.application.core.domain.ContactInfo
import com.test.application.core.domain.Location
import com.test.application.local_data.entity.avatar.ContactAvatarEntity
import com.test.application.local_data.entity.contacts.ContactsEntity
import com.test.application.local_data.entity.location.LocationEntity

fun ContactInfo.toEntity(locationId: Long, avatarId: Long): ContactsEntity {
    return ContactsEntity(
        id = this.id,
        cell = this.cell,
        phone = this.phone,
        birthday = this.birthday,
        email = this.email,
        locationId = locationId,
        avatarId = avatarId,
        name = this.name
    )
}

fun ContactsEntity.toDomain(location: Location, avatar: ContactAvatar): ContactInfo {
    return ContactInfo(
        id = this.id,
        cell = this.cell,
        phone = this.phone,
        birthday = this.birthday,
        email = this.email,
        location = location,
        name = this.name,
        picture = avatar
    )
}

fun Location.toEntity(): LocationEntity {
    return LocationEntity(
        city = this.city,
        country = this.country,
        postcode = this.postcode,
        state = this.state,
        street = this.street
    )
}

fun LocationEntity.toDomain(): Location {
    return Location(
        city = this.city,
        country = this.country,
        postcode = this.postcode,
        state = this.state,
        street = this.street
    )
}

fun ContactAvatar.toEntity(): ContactAvatarEntity {
    return ContactAvatarEntity(
        medium = this.medium
    )
}

fun ContactAvatarEntity.toDomain(): ContactAvatar {
    return ContactAvatar(
        medium = this.medium
    )
}