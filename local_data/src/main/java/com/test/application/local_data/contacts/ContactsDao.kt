package com.test.application.local_data.contacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.test.application.core.domain.ContactInfo
import com.test.application.local_data.mapper.toEntity

@Dao
interface ContactsDao {

    @Insert
    fun insertLocation(location: LocationEntity): Long

    @Insert
    fun insertAvatar(avatar: ContactAvatarEntity): Long

    @Insert
    fun insertContact(contact: ContactsEntity)

    @Transaction
    fun insertContact(contactInfo: ContactInfo) {
        val locationEntity = contactInfo.location.toEntity()
        val avatarEntity = contactInfo.picture.toEntity()

        val locationId = insertLocation(locationEntity)
        val avatarId = insertAvatar(avatarEntity)

        val contactEntity = contactInfo.toEntity(locationId, avatarId)
        insertContact(contactEntity)
    }

    @Transaction
    fun insertContacts(contacts: List<ContactInfo>) {
        contacts.forEach { insertContact(it) }
    }

    @Transaction
    @Query("SELECT * FROM contact_info")
    fun getAllContacts(): List<ContactsEntity>

    @Transaction
    @Query("SELECT * FROM contact_info WHERE id = :contactId")
    fun getContactById(contactId: String): FullContactDetails?
}