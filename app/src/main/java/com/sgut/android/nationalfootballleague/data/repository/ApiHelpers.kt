package com.sgut.android.nationalfootballleague.data.repository

import retrofit2.Response
import timber.log.Timber

private suspend fun <T, R> makeApiCall(
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R,
    default: R
): R {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let { transform(it) } ?: default
        } else {
            Timber.e("API call failed: ${response.errorBody()?.string()}")
            default
        }
    } catch (e: Exception) {
        Timber.e("API call exception: ${e.stackTraceToString()}")
        default
    }
}