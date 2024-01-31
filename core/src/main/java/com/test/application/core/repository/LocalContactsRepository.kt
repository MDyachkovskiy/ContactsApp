package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo

interface LocalContactsRepository {
    suspend fun getAllContacts(): List<ContactInfo>
    suspend fun insertContacts(contacts: List<ContactInfo>)
}