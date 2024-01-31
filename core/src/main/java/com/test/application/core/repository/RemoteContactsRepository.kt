package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo

interface RemoteContactsRepository {
    suspend fun getAllContacts() : List<ContactInfo>
}