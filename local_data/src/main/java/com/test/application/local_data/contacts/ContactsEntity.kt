package com.test.application.local_data.contacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "contact_info",
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = ["locationId"],
            childColumns = ["locationId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ContactAvatarEntity::class,
            parentColumns = ["avatarId"],
            childColumns = ["avatarId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ContactsEntity (
    @PrimaryKey val id: String,
    val cell: String,
    var phone: String,
    val birthday: String,
    val email: String,
    @ColumnInfo(index = true) val locationId: Long,
    @ColumnInfo(index = true) val avatarId: Long,
    val name: String
)