package com.example.mvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private const val baseUrl = "https://jsonplaceholder.typicode.com/"

        fun getApiClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val apiService = getApiClient().create(ApiService::class.java)
    }
}