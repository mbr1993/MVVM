package com.example.mvvm.api

import com.example.mvvm.model.BaseResponse
import com.example.mvvm.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getAllUsers(): Call<List<User>>

    @GET("users/{userId}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 343be102ec319de65d12811f445b93a0faa5e2a33d66dfb976e1d0ef7717d3ea"
    )
    fun getUser(@Path("userId") userId: String): Call<User>


    @POST("create_user")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 343be102ec319de65d12811f445b93a0faa5e2a33d66dfb976e1d0ef7717d3ea"
    )
    fun createUser(@Body user: User): Call<BaseResponse>

    @PATCH("users/{userId}")
    @Headers(
        "Accept:application/json",
        "Content-Type:application/json",
        "Authorization: Bearer 343be102ec319de65d12811f445b93a0faa5e2a33d66dfb976e1d0ef7717d3ea"
    )
    fun updateUser(@Path("userId") userId: String, @Body updatedUser: User): Call<BaseResponse>
}