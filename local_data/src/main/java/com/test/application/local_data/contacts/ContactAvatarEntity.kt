package com.test.application.local_data.contacts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_avatars")
data class ContactAvatarEntity(
    @PrimaryKey(autoGenerate = true) val avatarId: Long = 0,
    val medium: String,
    val thumbnail: String
)