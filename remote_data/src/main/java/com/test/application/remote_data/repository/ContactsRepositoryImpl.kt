package com.test.application.remote_data.repository

import com.test.application.core.domain.ContactInfo
import com.test.application.core.repository.RemoteContactsRepository
import com.test.application.remote_data.api.ContactsApi
import com.test.application.remote_data.mapper.toContactInfoList
import com.test.application.remote_data.utils.RESULT_COUNT

class ContactsRepositoryImpl(
    private val contactsApi: ContactsApi
) : RemoteContactsRepository {
    override suspend fun getAllContacts(): List<ContactInfo> {
        val contactsDTO =contactsApi.getContactsData(RESULT_COUNT)
        return contactsDTO.toContactInfoList()
    }
}