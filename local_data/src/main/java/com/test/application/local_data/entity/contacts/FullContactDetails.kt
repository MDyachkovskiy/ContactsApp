package com.test.application.local_data.entity.contacts

import androidx.room.Embedded
import androidx.room.Relation
import com.test.application.local_data.entity.avatar.ContactAvatarEntity
import com.test.application.local_data.entity.location.LocationEntity

data class FullContactDetails(
    @Embedded val contact: ContactsEntity,
    @Relation(
        parentColumn = "locationId",
        entityColumn = "locationId"
    )
    val location: LocationEntity,
    @Relation(
        parentColumn = "avatarId",
        entityColumn = "avatarId"
    )
    val avatar: ContactAvatarEntity
)