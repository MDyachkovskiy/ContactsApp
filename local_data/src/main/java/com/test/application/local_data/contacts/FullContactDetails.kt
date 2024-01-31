package com.test.application.local_data.contacts

import androidx.room.Embedded
import androidx.room.Relation

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