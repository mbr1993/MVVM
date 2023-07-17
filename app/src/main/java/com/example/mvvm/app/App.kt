package com.example.mvvm.app

import android.app.Application
import com.example.mvvm.provider.MainViewModelProvider

class App : Application() {

    lateinit var mainViewModelProvider: MainViewModelProvider

    override fun onCreate() {
        super.onCreate()
        mainViewModelProvider = MainViewModelProvider(this)
    }
}