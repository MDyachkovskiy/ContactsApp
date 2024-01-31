package com.test.application.contacts_list_screen.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.test.application.contacts_list_screen.databinding.FragmentContactsListBinding
import com.test.application.core.domain.ContactInfo
import com.test.application.core.ui.BaseFragment
import com.test.application.core.utils.AppState
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.contacts_list_screen.adapter.ContactsListAdapter
import androidx.fragment.app.viewModels
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_CONTACT_ID

class ContactsListFragment : BaseFragment<AppState, List<ContactInfo>, FragmentContactsListBinding>(
    FragmentContactsListBinding::inflate
) {

    private val viewModel: ContactsListViewModel by viewModels()
    private lateinit var contactsAdapter: ContactsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getContacts()
    }

    override fun setupData(data: List<ContactInfo>) {
        initRV(data)
    }

    private fun initRV(data: List<ContactInfo>) {
        contactsAdapter = ContactsListAdapter()
        binding.rvContacts.adapter = contactsAdapter
        binding.rvContacts.layoutManager = LinearLayoutManager(requireContext())
        contactsAdapter.updateContacts(data)
        handleNavigationListener()
    }

    private fun handleNavigationListener() {
        contactsAdapter.listener = { contactId ->
            val bundle = bundleOf(KEY_CONTACT_ID to contactId)
            (activity as Navigator).navigateFromContactsListToDetails(bundle)
        }
    }
}