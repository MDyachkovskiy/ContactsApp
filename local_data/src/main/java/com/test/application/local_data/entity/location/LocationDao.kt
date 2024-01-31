package com.test.application.local_data.entity.location

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE locationId = :id")
    fun getLocationById(id: Long): LocationEntity?
}