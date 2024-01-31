package com.test.application.local_data.repository

import com.test.application.core.repository.ContactDetailsRepository
import com.test.application.core.utils.AppState
import com.test.application.core.utils.ErrorType
import com.test.application.local_data.entity.contacts.ContactsDao
import com.test.application.local_data.mapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ContactDetailsRepositoryImpl (
    private val contactsDao: ContactsDao
) : ContactDetailsRepository {
    override suspend fun getContactDetails(contactId: String): Flow<AppState> = flow {
        emit(AppState.Loading)
        try {
            val fullContactDetails = withContext(Dispatchers.IO) {
                contactsDao.getContactById(contactId)
            }
            val appState = if (fullContactDetails != null) {
                val contactInfo = fullContactDetails.contact.toDomain(
                    fullContactDetails.location.toDomain(),
                    fullContactDetails.avatar.toDomain()
                )
                AppState.Success(contactInfo)
            } else {
                AppState.Error(ErrorType.EmptyData)
            }
            emit(appState)
        } catch (e: Exception) {
            emit(AppState.Error(ErrorType.UnknownError(e)))
        }
    }
}