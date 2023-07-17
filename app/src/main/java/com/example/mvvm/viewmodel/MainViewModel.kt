package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.database.PostEntity
import com.example.mvvm.repository.PostRepository
import com.example.mvvm.model.utility.NetworkResult

class MainViewModel(private val repository: PostRepository) : ViewModel() {
    fun getAllPosts(): LiveData<NetworkResult<List<PostEntity>>> {
        return repository.getAllPosts()
    }
}