package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo
import com.test.application.core.utils.EmptyDataException

class ContactsInteractorImpl(
    private val localContactsRepository: LocalContactsRepository,
    private val remoteContactsRepository: RemoteContactsRepository
) : ContactsInteractor {
    override suspend fun getAllContacts(): List<ContactInfo> {
        val localContacts = localContactsRepository.getAllContacts()
        return localContacts.ifEmpty {
            val networkContacts = remoteContactsRepository.getAllContacts()
            if (networkContacts.isNotEmpty()) {
                localContactsRepository.insertContacts(networkContacts)
                networkContacts
            } else {
                throw EmptyDataException()
            }
        }
    }
}