package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo

interface ContactsRepository {
    suspend fun getAllContacts() : List<ContactInfo>
}