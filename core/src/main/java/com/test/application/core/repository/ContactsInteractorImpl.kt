package com.test.application.core.repository

import com.test.application.core.domain.ContactInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactsInteractorImpl(
    private val localContactsRepository: LocalContactsRepository,
    private val remoteContactsRepository: RemoteContactsRepository
) : ContactsInteractor {
    override suspend fun getAllContacts(): Flow<List<ContactInfo>> {
        return localContactsRepository.getAllContacts()
            .map { localContacts ->
                localContacts.ifEmpty {
                    val networkContacts = remoteContactsRepository.getAllContacts()
                    localContactsRepository.insertContacts(networkContacts)
                    networkContacts
                }
            }
    }
}