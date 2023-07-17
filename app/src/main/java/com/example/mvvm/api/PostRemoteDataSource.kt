package com.example.mvvm.api

import com.example.mvvm.model.Post

class PostRemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllPosts() : List<Post>{
        return apiService.getPosts()
    }
}