package com.test.application.local_data.entity.contacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.test.application.core.domain.ContactInfo
import com.test.application.local_data.entity.avatar.ContactAvatarEntity
import com.test.application.local_data.entity.location.LocationEntity
import com.test.application.local_data.mapper.toEntity

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvatar(avatar: ContactAvatarEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactsEntity)

    @Transaction
    suspend fun insertContact(contactInfo: ContactInfo) {
        val locationEntity = contactInfo.location.toEntity()
        val avatarEntity = contactInfo.picture.toEntity()

        val locationId = insertLocation(locationEntity)
        val avatarId = insertAvatar(avatarEntity)

        val contactEntity = contactInfo.toEntity(locationId, avatarId)
        insertContact(contactEntity)
    }

    @Transaction
    suspend fun insertContacts(contacts: List<ContactInfo>) {
        contacts.forEach { insertContact(it) }
    }

    @Transaction
    @Query("SELECT * FROM contact_info")
    fun getAllContacts(): List<ContactsEntity>

    @Transaction
    @Query("SELECT * FROM contact_info WHERE id = :contactId")
    fun getContactById(contactId: String): FullContactDetails?

    @Query("DELETE FROM contact_info")
    suspend fun clearContacts()
}