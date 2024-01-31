package com.test.application.local_data.entity.location

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val locationId: Long = 0,
    val city : String,
    val country: String,
    val postcode: String,
    val state: String,
    val street: String
)