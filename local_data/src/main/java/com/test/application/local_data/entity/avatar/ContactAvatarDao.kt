package com.test.application.local_data.entity.avatar

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ContactAvatarDao {
    @Query("SELECT * FROM contact_avatars WHERE avatarId = :id")
    fun getAvatarById(id: Long): ContactAvatarEntity?
}