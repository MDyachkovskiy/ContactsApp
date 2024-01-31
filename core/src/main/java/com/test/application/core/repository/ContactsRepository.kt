package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo
import com.test.application.core.utils.AppState

interface ContactsRepository {
    fun getAllContacts() : List<ContactInfo>
    fun getContactDetails(contactId: String) : AppState
}