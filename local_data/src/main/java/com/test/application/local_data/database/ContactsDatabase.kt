package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.application.local_data.entity.avatar.ContactAvatarDao
import com.test.application.local_data.entity.avatar.ContactAvatarEntity
import com.test.application.local_data.entity.location.LocationDao
import com.test.application.local_data.entity.contacts.ContactsDao
import com.test.application.local_data.entity.contacts.ContactsEntity
import com.test.application.local_data.entity.location.LocationEntity

@Database(entities = [ContactsEntity::class, LocationEntity::class, ContactAvatarEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactsDao
    abstract fun locationDao(): LocationDao
    abstract fun contactAvatarDao(): ContactAvatarDao
}