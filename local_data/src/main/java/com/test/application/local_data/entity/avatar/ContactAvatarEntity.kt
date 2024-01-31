package com.test.application.local_data.entity.avatar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_avatars")
data class ContactAvatarEntity(
    @PrimaryKey(autoGenerate = true) val avatarId: Long = 0,
    val large: String
)