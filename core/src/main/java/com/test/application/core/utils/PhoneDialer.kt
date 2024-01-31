package com.test.application.core.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.test.application.core.R

class PhoneDialer(
    private val context: Context
) {
    private val CALL_PERMISSION_REQUEST_CODE = 123

    fun dialPhoneNumber(phoneNumber: String) {
        if (checkCallPermission()) {
            showConfirmationDialog(phoneNumber)
        } else {
            requestCallPermission()
        }
    }

    private fun checkCallPermission(): Boolean {
        val permission = Manifest.permission.CALL_PHONE
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context, permission)
    }

    private fun requestCallPermission() {
        val permission = Manifest.permission.CALL_PHONE
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission),
            CALL_PERMISSION_REQUEST_CODE)
    }

    private fun showConfirmationDialog(phoneNumber: String) {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.confirm_call))
            setMessage(context.getString(R.string.confirm_call_message, phoneNumber))
            setPositiveButton(context.getString(R.string.call)) { _, _ ->
                performCall(phoneNumber)
            }
            setNegativeButton(context.getString(R.string.cancel), null)
        }.show()
    }

    private fun performCall(phoneNumber: String) {
        val cleanedPhoneNumber = formatPhoneNumber(phoneNumber)
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$cleanedPhoneNumber")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        val digitsOnly = phoneNumber.replace(Regex("[^0-9]"), "")
        if (digitsOnly.length > 10) {
            return digitsOnly.substring(digitsOnly.length - 10)
        }
        return digitsOnly
    }
}