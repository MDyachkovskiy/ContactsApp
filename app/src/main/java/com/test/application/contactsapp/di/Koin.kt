package com.test.application.contactsapp.di

import com.test.application.contact_details.ContactDetailViewModel
import com.test.application.contacts_list_screen.view.ContactsListViewModel
import com.test.application.core.repository.ContactsRepository
import com.test.application.remote_data.api.ContactsApi
import com.test.application.remote_data.repository.ContactsRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ContactsApi::class.java)
    }
}

val repositoryModule = module {
    single<ContactsRepository> { ContactsRepositoryImpl(contactsApi = get()) }

}



val viewModelModule = module {
    viewModel { ContactsListViewModel(contactsRepository = get()) }
    viewModel { ContactDetailViewModel(contactsRepository = get()) }
}