package com.example.mvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheAllPosts(list: List<PostEntity>)

    @Query("SELECT * FROM PostEntity")
    fun getAllPosts(): LiveData<List<PostEntity>>
}