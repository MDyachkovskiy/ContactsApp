package com.test.application.contacts_list_screen.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.core.repository.ContactsInteractor
import com.test.application.core.utils.AppState
import com.test.application.core.utils.EmptyDataException
import com.test.application.core.utils.ErrorType
import kotlinx.coroutines.launch
import java.io.IOException

class ContactsListViewModel(
    private val contactsInteractor: ContactsInteractor
) : ViewModel() {

    private val contactsLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData(): LiveData<AppState> = contactsLiveData

    fun getContacts() {
        contactsLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val contacts = contactsInteractor.getAllContacts()
                contactsLiveData.postValue(AppState.Success(contacts))
            } catch (e: EmptyDataException) {
                contactsLiveData.postValue(AppState.Error(ErrorType.EmptyData))
            } catch (e: IOException) {
                contactsLiveData.postValue(AppState.Error(ErrorType.NetworkError))
            } catch (e: Throwable) {
                contactsLiveData.postValue(AppState.Error(ErrorType.UnknownError(e)))
            }
        }
    }
}