package com.test.application.contact_details

import com.test.application.core.repository.ContactDetailsRepository
import com.test.application.core.ui.BaseViewModel
import com.test.application.core.utils.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactDetailViewModel(
    private val contactDetailsRepository: ContactDetailsRepository
) : BaseViewModel<AppState>() {

    private val _stateFlow = MutableStateFlow<AppState>(AppState.Loading)
    val stateFlow: StateFlow<AppState> get() = _stateFlow.asStateFlow()

    fun getContactDetails(contactId: String) {
        viewModelCoroutineScope.launch {
            contactDetailsRepository.getContactDetails(contactId)
                .collect { appState ->
                    _stateFlow.value = appState
                }
        }
    }
}