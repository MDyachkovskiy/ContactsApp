package com.test.application.remote_data.api

import com.test.application.remote_data.dto.ContactInfoDTO
import com.test.application.remote_data.utils.CONTACTS_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsApi {
    @GET(CONTACTS_URL)
    suspend fun getContactsData(
        @Query("results") results: Int
    ): ContactInfoDTO
}