package com.test.application.core.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.test.application.core.R

class EmailSender(
    private val context: Context
) {
    fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            showNoEmailAppDialog()
        }
    }

    private fun showNoEmailAppDialog() {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.no_email_app_title))
            setMessage(context.getString(R.string.no_email_app_message))
            setPositiveButton(context.getString(R.string.ok), null)
        }.show()
    }
}