package com.test.application.contact_details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.application.contact_details.databinding.FragmentContactDetailsBinding
import com.test.application.core.domain.ContactInfo
import com.test.application.core.ui.BaseFragment
import com.test.application.core.utils.AppState
import com.test.application.core.utils.KEY_CONTACT_ID
import coil.load
import com.test.application.core.navigation.OnBackPressInDetails
import com.test.application.core.utils.PhoneDialer
import com.test.application.core.utils.formatDateString
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactDetailFragment : BaseFragment<AppState, ContactInfo, FragmentContactDetailsBinding>(
    FragmentContactDetailsBinding::inflate
) {

    private var backButtonListener: OnBackPressInDetails? = null
    private var backPressedCallback: OnBackPressedCallback? = null

    private val viewModel: ContactDetailViewModel by viewModel()
    private lateinit var phoneDialer: PhoneDialer

    private val contactId: String by lazy {
        arguments?.getString(KEY_CONTACT_ID)
            ?: throw IllegalArgumentException(getString(R.string.incorrect_contact_id))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackPressInDetails) {
            backButtonListener = context
        } else {
            throw RuntimeException("$context must implement OnBackButtonListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        backButtonListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initBackButton()
        phoneDialer = PhoneDialer(requireContext())
    }

    private fun initBackButton() {
        val backButton = binding.backButton
        backButton.setOnClickListener {
            backButtonListener?.onBackButtonPressedInContactDetails()
        }

        backPressedCallback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                backButtonListener?.onBackButtonPressedInContactDetails()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            backPressedCallback as OnBackPressedCallback
        )
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { appState ->
                    renderData(appState)
                }
            }
        }
        viewModel.getContactDetails(contactId)
    }

    override fun setupData(data: ContactInfo) {
        initTextData(data)
        initAvatar(data)
    }

    private fun initAvatar(data: ContactInfo) {
        binding.personPhoto.load(data.picture.large){
            crossfade(true)
            placeholder(com.test.application.core.R.drawable.person_placeholder)
        }
    }

    private fun initTextData(contact: ContactInfo) {
        with(binding) {
            tvContactName.text = contact.name
            setContactPhone(tvCellPhone, contact.cell)

            tvEmail.text = contact.email
            setContactPhone(tvPhone, contact.phone)

            tvStreet.text = contact.location.street
            tvCity.text = contact.location.city
            tvState.text = contact.location.state
            tvCountry.text = contact.location.country

            tvDob.text = formatDateString(contact.birthday)
        }
    }

    private fun setContactPhone(textView: TextView, phone: String) {
        textView.apply {
            text = phone
            paint.isUnderlineText = true
            setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
            setOnClickListener {
                phoneDialer.dialPhoneNumber(phone)
            }
        }
    }
}