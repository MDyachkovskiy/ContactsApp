package com.test.application.core.repository

import com.test.application.core.utils.AppState
import kotlinx.coroutines.flow.Flow

interface ContactDetailsRepository {
    suspend fun getContactDetails(contactId: String) : Flow<AppState>
}