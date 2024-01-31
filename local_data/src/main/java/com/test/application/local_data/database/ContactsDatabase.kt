package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.application.local_data.contacts.ContactAvatarEntity
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.contacts.ContactsEntity
import com.test.application.local_data.contacts.LocationEntity

@Database(entities = [ContactsEntity::class, LocationEntity::class, ContactAvatarEntity::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactsDao
}