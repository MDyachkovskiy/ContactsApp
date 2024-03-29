package com.test.application.contacts_list_screen.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.application.contacts_list_screen.databinding.FragmentContactsListBinding
import com.test.application.core.domain.ContactInfo
import com.test.application.core.ui.BaseFragment
import com.test.application.core.utils.AppState
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.application.contacts_list_screen.R
import com.test.application.contacts_list_screen.adapter.ContactsListAdapter
import com.test.application.core.navigation.Navigator
import com.test.application.core.utils.KEY_CONTACT_ID
import com.test.application.core.utils.MapOpener
import com.test.application.core.utils.PhoneDialer
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactsListFragment : BaseFragment<AppState, List<ContactInfo>, FragmentContactsListBinding>(
    FragmentContactsListBinding::inflate
) {

    private val viewModel: ContactsListViewModel by viewModel()
    private lateinit var phoneDialer: PhoneDialer
    private lateinit var contactsAdapter: ContactsListAdapter
    private lateinit var mapOpener: MapOpener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        phoneDialer = PhoneDialer(requireContext())
        mapOpener = MapOpener()
        handleRefreshButton()
    }

    private fun handleRefreshButton() {
        binding.btnRefresh.setOnClickListener {
            showRefreshConfirmationDialog()
        }
    }

    private fun showRefreshConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.refresh_contacts_title))
            .setMessage(getString(R.string.refresh_contacts_message))
            .setPositiveButton(getString(R.string.refresh)) { _, _ ->
                viewModel.refreshContacts()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect {appState ->
                    renderData(appState)
                }
            }
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
        handleDialListener()
        handleAddressListener()
    }

    private fun handleAddressListener() {
        contactsAdapter.onAddressClickListener = { latitude, longitude ->
            mapOpener.openMap(requireContext(), latitude, longitude)
        }
    }

    private fun handleDialListener() {
        contactsAdapter.onPhoneClickListener = { phoneNumber ->
            phoneDialer.dialPhoneNumber(phoneNumber)
        }
    }

    private fun handleNavigationListener() {
        contactsAdapter.listener = { contactId ->
            val bundle = bundleOf(KEY_CONTACT_ID to contactId)
            (activity as Navigator).navigateFromContactsListToDetails(bundle)
        }
    }
}