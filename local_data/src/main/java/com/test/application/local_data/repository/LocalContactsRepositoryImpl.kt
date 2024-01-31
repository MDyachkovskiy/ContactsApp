package com.test.application.local_data.repository

import com.test.application.core.domain.ContactInfo
import com.test.application.core.repository.LocalContactsRepository
import com.test.application.local_data.contacts.ContactsDao

class LocalContactsRepositoryImpl(
    private val contactsDao: ContactsDao
) : LocalContactsRepository {

    override suspend fun getAllContacts(): List<ContactInfo> {
        return contactsDao.getAllContacts()
    }

    override suspend fun insertContacts(contacts: List<ContactInfo>) {
        contactsDao.insertContacts(contacts)
    }
}