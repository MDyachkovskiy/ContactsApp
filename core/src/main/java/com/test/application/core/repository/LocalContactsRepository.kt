package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo
import kotlinx.coroutines.flow.Flow

interface LocalContactsRepository {
    fun getAllContacts(): Flow<List<ContactInfo>>
    suspend fun insertContacts(contacts: List<ContactInfo>)
}