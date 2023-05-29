package com.example.pokedex.data.repository

import com.example.pokedex.utils.NetworkResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@Suppress("TooGenericExceptionCaught")
open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResource.Success(apiCall.invoke())
            } catch (exception: Exception) {
                when (exception) {
                    is HttpException -> {
                        NetworkResource.Failure(
                            false,
                            exception.code(),
                            exception.response()?.errorBody()
                        )
                    }
                    else -> {
                        NetworkResource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}