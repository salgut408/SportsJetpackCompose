package com.sgut.android.nationalfootballleague.data.help

sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T? = null): NetworkResult<T>()
    data class Loading<out T>(val data: T? = null): NetworkResult<T>()
    data class Error(val errorMessage: String, val errorCode: String): NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this){
            is Success<*> -> "Success[data = $data]"
            is Loading<*> -> "Loading[data = $data]"
            is Error -> "Error :( [errorMsg =$errorMessage, errorCode = $errorCode]"
        }
    }

}