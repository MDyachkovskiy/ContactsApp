package com.test.application.core.repository

import com.test.application.core.utils.AppState

interface ContactDetailsRepository {
    suspend fun getContactDetails(contactId: String) : AppState

}