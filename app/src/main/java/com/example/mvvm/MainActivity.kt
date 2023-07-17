package com.example.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.api.ApiClient
import com.example.mvvm.api.PostRemoteDataSource
import com.example.mvvm.database.AppDatabase
import com.example.mvvm.database.PostLocalDataSource
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.repository.PostRepository
import com.example.mvvm.utility.NetworkHelper
import com.example.mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postDao = AppDatabase.getDatabase(this).postDao()
        val localDataSource = PostLocalDataSource(postDao)
        val apiService = ApiClient.apiService
        val remoteDataSource = PostRemoteDataSource(apiService)
        val networkHelper = NetworkHelper(this)
        val repository = PostRepository(networkHelper, localDataSource, remoteDataSource)
        viewModel = MainViewModel(repository)
    }
}