package com.test.application.local_data.repository

import com.test.application.core.domain.ContactAvatar
import com.test.application.core.domain.ContactInfo
import com.test.application.core.domain.Location
import com.test.application.core.repository.LocalContactsRepository
import com.test.application.local_data.entity.location.LocationDao
import com.test.application.local_data.entity.contacts.ContactsDao
import com.test.application.local_data.entity.avatar.ContactAvatarDao
import com.test.application.local_data.mapper.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class LocalContactsRepositoryImpl(
    private val contactsDao: ContactsDao,
    private val locationDao: LocationDao,
    private val avatarDao: ContactAvatarDao
) : LocalContactsRepository {

    override fun getAllContacts(): Flow<List<ContactInfo>> {
        return flow {
            val contactEntities = contactsDao.getAllContacts()
            val contactInfoList = contactEntities.map { contactEntity ->
                val location = locationDao.getLocationById(contactEntity.locationId)?.toDomain() ?: Location()
                val avatar = avatarDao.getAvatarById(contactEntity.avatarId)?.toDomain() ?: ContactAvatar()
                contactEntity.toDomain(location, avatar)
            }
            emit(contactInfoList)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertContacts(contacts: List<ContactInfo>) {
        withContext(Dispatchers.IO) { contactsDao.insertContacts(contacts) }
    }
}