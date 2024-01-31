package com.test.application.contacts_list_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.contacts_list_screen.databinding.ItemContactBinding
import com.test.application.core.domain.ContactInfo

class ContactsListAdapter : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    private var contactsList: MutableList<ContactInfo> = mutableListOf()

    var listener: ((id: String) -> Unit)? = null
    var onPhoneClickListener: ((phoneNumber: String) -> Unit)? = null
    var onAddressClickListener: ((latitude: Float, longitude: Float) -> Unit)? = null

    fun updateContacts(newContacts: List<ContactInfo>) {
        val diffCallback = ContactsDiffUtils(contactsList, newContacts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        contactsList.clear()
        contactsList.addAll(newContacts)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsListAdapter.ViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsListAdapter.ViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount() = contactsList.size

    inner class ViewHolder(
        private val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactInfo){
            setContactName(contact.name)
            setContactAddress(contact.location.street)
            setContactPhone(contact.cell)
            setContactAvatar(contact.picture.large)

            binding.root.setOnClickListener {
                listener?.invoke(contact.id)
            }

            binding.tvAddress.setOnClickListener {
                onAddressClickListener?.invoke(contact.location.latitude, contact.location.longitude)
            }
        }

        private fun setContactAvatar(imageUrl: String) {
            binding.contactAvatar.load(imageUrl) {
                crossfade(true)
                placeholder(com.test.application.core.R.drawable.person_placeholder)
            }
        }

        private fun setContactPhone(phone: String) {
            binding.tvPhone.apply {
                text = phone
                paint.isUnderlineText = true
                setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
                setOnClickListener {
                    onPhoneClickListener?.invoke(phone)
                }
            }
        }

        private fun setContactAddress(address: String) {
            binding.tvAddress.apply {
                text = address
                paint.isUnderlineText = true
            }
        }

        private fun setContactName(name: String) {
            binding.tvName.text = name
        }
    }
}