package com.test.application.contacts_list_screen.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.application.core.domain.ContactInfo

class ContactsDiffUtils(
    private val oldList: List<ContactInfo>,
    private val newList: List<ContactInfo>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}