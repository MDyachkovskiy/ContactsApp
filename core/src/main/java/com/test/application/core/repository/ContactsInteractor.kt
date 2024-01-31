package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo
import kotlinx.coroutines.flow.Flow

interface ContactsInteractor {
    suspend fun getAllContacts(): Flow<List<ContactInfo>>
}