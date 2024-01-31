package com.test.application.core.utils

import com.test.application.core.R

sealed class ErrorType(val messageResId: Int) {
    object EmptyData : ErrorType(R.string.error_empty_data)
    object NetworkError : ErrorType(R.string.error_network)
    class UnknownError(val throwable: Throwable) : ErrorType(R.string.error_unknown)
}