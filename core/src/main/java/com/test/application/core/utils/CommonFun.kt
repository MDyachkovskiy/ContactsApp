package com.test.application.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDateString(originalDateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val date = inputFormat.parse(originalDateString)
    return if (date != null) outputFormat.format(date) else ""
}