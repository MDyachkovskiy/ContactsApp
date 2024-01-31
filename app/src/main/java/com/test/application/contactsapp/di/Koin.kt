package com.test.application.contactsapp.di

import android.app.Application
import androidx.room.Room
import com.test.application.contact_details.ContactDetailViewModel
import com.test.application.contacts_list_screen.view.ContactsListViewModel
import com.test.application.core.repository.ContactsInteractor
import com.test.application.core.repository.ContactsInteractorImpl
import com.test.application.core.repository.LocalContactsRepository
import com.test.application.core.repository.RemoteContactsRepository
import com.test.application.local_data.database.ContactsDatabase
import com.test.application.local_data.repository.LocalContactsRepositoryImpl
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
    single<ContactsInteractor> { ContactsInteractorImpl(get(), get()) }
    single<LocalContactsRepository> { LocalContactsRepositoryImpl(get()) }
    single<RemoteContactsRepository> { ContactsRepositoryImpl(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(get<Application>(), ContactsDatabase::class.java, "contacts_database")
            .build()
    }
    single { get<ContactsDatabase>().contactDao() }
}


val viewModelModule = module {
    viewModel { ContactsListViewModel(contactsInteractor = get()) }
    viewModel { ContactDetailViewModel(contactsRepository = get()) }
}