package com.example.mvvm.repository

import com.example.mvvm.api.ApiService
import com.example.mvvm.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    fun getAllUser(): List<User> {
        val list: MutableList<User> = ArrayList()
        val api = ApiClient.getApiClient().create(ApiService::class.java)
        api.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    list.addAll(response.body() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return list
    }
}