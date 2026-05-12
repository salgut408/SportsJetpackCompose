package com.sgut.android.nationalfootballleague.data.help

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenNetworkFlow @Inject constructor(
    @ApplicationContext val applicationContext: Context
){
    companion object {
        const val UNKNOWN_ERROR = "IDK THIS ERROR"
        const val ERROR_CODE = "123"

    }

    inline fun <reified T> getNetworkResult(
        noinline networkApiCall: suspend () -> Response<T>,
    ) = getNetworkBoundResourceResult<T, T>(
        networkApiCall = networkApiCall,
    )

    inline fun <reified T, DomainType> getNetworkResult(
        noinline networkApiCall: suspend () -> Response<DomainType>,
        noinline saveCallResult: (suspend (T) -> Unit)? = null    ) = getNetworkBoundResourceResult(
        networkApiCall = networkApiCall,
        saveCallResult = saveCallResult
    )

    inline fun <reified T, DomainType> getNetworkResult(
        noinline networkApiCall: suspend () -> Response<DomainType>,
        noinline transformFromDomainType: ((DomainType) -> T)? = null,
        noinline saveCallResult: (suspend (T) -> Unit)? = null    ) = getNetworkBoundResourceResult(
        networkApiCall = networkApiCall,
        transformFromDomainType = transformFromDomainType,
        saveCallResult = saveCallResult
        )



    // big
    inline fun <reified  T, DomainType> getNetworkBoundResourceResult(
        noinline dbQuery: (() -> T)? = null,
        noinline networkApiCall: suspend () -> Response<DomainType>,
        noinline transformFromDomainType: ((DomainType) -> T)? = null,
        noinline saveCallResult: (suspend (T) -> Unit)? = null
    ) = flow {
        emit(NetworkResult.Loading(dbQuery?.invoke()))

        val networkResponse = try {
            networkApiCall()
        } catch (e: Exception){
            emit(NetworkResult.Error(e.localizedMessage ?: UNKNOWN_ERROR, ERROR_CODE))
            return@flow
        }

        val result = if (networkResponse.isSuccessful){
            networkResponse.body()?.let {
                val entity = if (transformFromDomainType != null) {
                    transformFromDomainType.invoke(it)
                } else {
                    it as T
                }
                saveCallResult?.invoke(entity)
                NetworkResult.Success(entity)
            } ?: NetworkResult.Success(null)
        } else {
            networkResponse.errorBody()?.string()?.let {
                var errorMsg: String?
                var errorCode: String?
                try {
                    val errorObj = Gson().fromJson(
                        it,
                        ErrorResponse::class.java
                    )
                    errorMsg = errorObj.error
                    errorCode = errorObj.code
                } catch (e: Exception){
                    errorMsg = it
                    errorCode = ERROR_CODE
                }
                if (errorMsg.isNullOrEmpty()){
                    NetworkResult.Error(UNKNOWN_ERROR, ERROR_CODE)
                } else {
                    NetworkResult.Error(
                        UNKNOWN_ERROR,
                        errorCode ?: ERROR_CODE
                    )
                }
            } ?:NetworkResult.Error(UNKNOWN_ERROR, ERROR_CODE)
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}


data class ErrorResponse(
    @SerializedName(value = "error", alternate = ["message"])
    val error:String,
    val code: String
)