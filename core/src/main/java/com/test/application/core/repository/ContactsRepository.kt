package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo

interface ContactsRepository {
    fun getAllContacts() : List<ContactInfo>
}