package com.test.application.contacts_list_screen.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.ContactsRepository
import com.test.application.core.utils.AppState
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    private val contactsLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData(): LiveData<AppState> = contactsLiveData

    fun getContacts() {
        contactsLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val contacts = contactsRepository.getAllContacts()
                contactsLiveData.postValue(AppState.Success(contacts))
            } catch( e: Exception) {
                contactsLiveData.postValue(AppState.Error(e))
            }
        }
    }
}