package com.test.application.local_data.repository

import com.test.application.core.repository.ContactDetailsRepository
import com.test.application.core.utils.AppState
import com.test.application.core.utils.ErrorType
import com.test.application.local_data.contacts.ContactsDao
import com.test.application.local_data.mapper.toDomain

class ContactDetailsRepositoryImpl (
    private val contactsDao: ContactsDao
) : ContactDetailsRepository {
    override suspend fun getContactDetails(contactId: String): AppState {
        return try {
            val fullContactDetails = contactsDao.getContactById(contactId)
            if (fullContactDetails != null) {
                val contactInfo = fullContactDetails.contact.toDomain(
                    fullContactDetails.location.toDomain(),
                    fullContactDetails.avatar.toDomain()
                )
                AppState.Success(contactInfo)
            } else {
                AppState.Error(ErrorType.EmptyData)
            }
        } catch (e: Exception) {
            AppState.Error(ErrorType.UnknownError(e))
        }
    }
}