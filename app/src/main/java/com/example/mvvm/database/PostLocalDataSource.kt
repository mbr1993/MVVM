package com.example.mvvm.database

import androidx.lifecycle.LiveData

class PostLocalDataSource(private val postDao: PostDao) {
    suspend fun cachePosts(list: List<PostEntity>) {
        postDao.cacheAllPosts(list)
    }

    fun getAllPosts() : LiveData<List<PostEntity>>{
        return postDao.getAllPosts()
    }
}