package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo

interface ContactsInteractor {
    suspend fun getAllContacts(): List<ContactInfo>
}