package com.example.mvvm.provider

import android.content.Context
import com.example.mvvm.api.ApiClient
import com.example.mvvm.api.PostRemoteDataSource
import com.example.mvvm.database.AppDatabase
import com.example.mvvm.database.PostLocalDataSource
import com.example.mvvm.repository.PostRepository
import com.example.mvvm.model.utility.NetworkHelper
import com.example.mvvm.viewmodel.MainViewModelFactory

class MainViewModelProvider(private val context: Context) {
    val postLocalDataSource = PostLocalDataSource(AppDatabase.getDatabase(context).postDao())
    val postRemoteDataSource = PostRemoteDataSource(ApiClient.apiService)
    val networkHelper = NetworkHelper(context)
    val postRepository = PostRepository(networkHelper, postLocalDataSource, postRemoteDataSource)
    val mainViewModelFactory = MainViewModelFactory(postRepository)

}