package com.test.application.contact_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.ContactsRepository
import com.test.application.core.utils.AppState
import kotlinx.coroutines.launch

class ContactDetailViewModel(
    private val contactsRepository: ContactsRepository
) : ViewModel() {
    private val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveData

    fun getContactDetails(contactId: String) {
        viewModelScope.launch {
            liveData.value = AppState.Loading
            liveData.value = contactsRepository.getContactDetails(contactId)
        }
    }
}