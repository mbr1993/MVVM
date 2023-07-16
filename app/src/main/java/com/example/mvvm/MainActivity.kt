package com.example.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val usernameMaxLength = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.editUsername.addTextChangedListener {
            viewModel.setUsername(it.toString())
        }
        binding.password.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
        binding.login.setOnClickListener {
            viewModel.onLoginClicked()
        }

        viewModel.getUserNameErrorMessage().observe(this) {
            binding.textErrorUsername.text = it
        }

        viewModel.getPasswordErrorMessage().observe(this) {
            binding.textErrorPassword.text = it
        }

        viewModel.getUsernameLengthCounter().observe(this) {
            if (it > usernameMaxLength) {
                binding.userNameCount.setTextColor(this.getColor(R.color.red))
            } else {
                binding.userNameCount.setTextColor(this.getColor(R.color.black))
            }
            binding.userNameCount.text = "$it/$usernameMaxLength"
        }
    }
}