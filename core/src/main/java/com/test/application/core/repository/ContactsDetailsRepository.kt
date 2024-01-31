package com.test.application.core.repository

import com.test.application.core.utils.AppState

interface ContactsDetailsRepository {
    fun getContactDetails(contactId: String) : AppState

}