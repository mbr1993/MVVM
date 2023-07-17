package com.example.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import com.example.mvvm.repository.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun getAllUser(): List<User> {
       return repository.getAllUser()
    }
}