package com.example.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.R

class MainViewModel(private val application: Application) : AndroidViewModel(application) {

    private var username = MutableLiveData<String>("")
    private var password = MutableLiveData<String>("")

    private var userNameErrorMessage = MutableLiveData<String>()
    private var passwordErrorMessage = MutableLiveData<String>()

    private var usernameCounter = MutableLiveData<Int>(0)

    fun setUsername(username: String) {
        userNameErrorMessage.value = ""
        this.username.value = username
        usernameCounter.value = username.length
    }

    fun setPassword(password: String) {
        passwordErrorMessage.value = ""
        this.password.value = password
    }

    fun onLoginClicked() {
        if (username.value.toString().isEmpty())
            userNameErrorMessage.value = application.getString(R.string.user_name_is_empty)
        else if (username.value.toString().length < 3)
            userNameErrorMessage.value = application.getString(R.string.user_min_length_error)
        else if (username.value.toString().length > 16)
            userNameErrorMessage.value = application.getString(R.string.user_max_length_error)

        if (password.value.toString().isEmpty())
            passwordErrorMessage.value = application.getString(R.string.password_is_empty)
        else if (password.value.toString().length < 4 || password.value.toString().length > 20)
            passwordErrorMessage.value = application.getString(R.string.password_error)
    }

    fun getUserNameErrorMessage(): LiveData<String> = userNameErrorMessage
    fun getPasswordErrorMessage(): LiveData<String> = passwordErrorMessage
    fun getUsernameLengthCounter(): LiveData<Int> = usernameCounter
}