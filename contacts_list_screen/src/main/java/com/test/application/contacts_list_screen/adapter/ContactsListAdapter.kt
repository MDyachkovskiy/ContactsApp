package com.test.application.contacts_list_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.application.contacts_list_screen.databinding.ItemContactBinding
import com.test.application.core.domain.ContactInfo

class ContactsListAdapter : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    private var contactsList: MutableList<ContactInfo> = mutableListOf()

    var listener: ((id: String) -> Unit)? = null

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
            with(binding) {
                tvName.text = contact.name
                tvAddress.text = contact.location.street
                tvPhone.text = contact.cell

                contactAvatar.load(contact.picture.thumbnail) {
                    crossfade(true)
                    placeholder(com.test.application.core.R.drawable.person_placeholder)
                }

                root.setOnClickListener {
                    listener?.invoke(contact.id)
                }
            }
        }
    }
}