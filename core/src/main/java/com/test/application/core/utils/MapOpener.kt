package com.test.application.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.test.application.core.R


class MapOpener {
    fun openMap(context: Context, latitude: Float, longitude: Float) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.map_opener_dialog_title))
            .setMessage(context.getString(R.string.map_opener_dialog_message))
            .setPositiveButton(context.getString(R.string.map_opener_dialog_positive_button)) { dialog, which ->
                launchMapIntent(context, latitude, longitude)
            }
            .setNegativeButton(context.getString(R.string.map_opener_dialog_negative_button), null)
            .show()
    }

    private fun launchMapIntent(context: Context, latitude: Float, longitude: Float) {
        val mapUri = Uri.parse("http://maps.google.com/maps?q=loc:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)
    }
}
