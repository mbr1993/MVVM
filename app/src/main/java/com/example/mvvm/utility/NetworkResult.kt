package com.example.mvvm.utility

data class NetworkResult<out T>(
    val status: NetworkStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(NetworkStatus.SUCCESS, data, null)
        }

        fun <T> error(message: String?): NetworkResult<T> {
            return NetworkResult(NetworkStatus.ERROR, null, message)
        }
        fun <T> loading() : NetworkResult<T>{
            return NetworkResult(NetworkStatus.LOADING, null, null)
        }
    }
}