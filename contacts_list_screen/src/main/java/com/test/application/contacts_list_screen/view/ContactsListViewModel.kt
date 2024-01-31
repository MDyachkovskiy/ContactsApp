package com.test.application.contacts_list_screen.view

import com.test.application.core.repository.ContactsInteractor
import com.test.application.core.ui.BaseViewModel
import com.test.application.core.utils.AppState
import com.test.application.core.utils.EmptyDataException
import com.test.application.core.utils.ErrorType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class ContactsListViewModel(
    private val contactsInteractor: ContactsInteractor
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow

    fun getContacts() {
        _stateFlow.value = AppState.Loading
        viewModelCoroutineScope.launch {
            try {
                val contactsFlow = contactsInteractor.getAllContacts()
                contactsFlow.collect { contacts ->
                    _stateFlow.value = AppState.Success(contacts)
                }
            } catch (e: EmptyDataException) {
                _stateFlow.value = AppState.Error(ErrorType.EmptyData)
            } catch (e: IOException) {
                _stateFlow.value = AppState.Error(ErrorType.NetworkError)
            } catch (e: Throwable) {
                _stateFlow.value = AppState.Error(ErrorType.UnknownError(e))
            }
        }
    }
}