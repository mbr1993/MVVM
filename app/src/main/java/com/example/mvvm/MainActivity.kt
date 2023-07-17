package com.example.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.app.App
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.model.utility.NetworkStatus
import com.example.mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provider = (application as App).mainViewModelProvider
        provider.mainViewModelFactory

        viewModel =
            ViewModelProvider(this, provider.mainViewModelFactory)[MainViewModel::class.java]

        viewModel.getAllPosts().observe(this) {
            when (it.status) {
                NetworkStatus.LOADING -> {
                    //show Progress bar
                    binding.progressBar.visibility = View.VISIBLE
                }

                NetworkStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.textView.text = it.data.toString()
                }

                NetworkStatus.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}