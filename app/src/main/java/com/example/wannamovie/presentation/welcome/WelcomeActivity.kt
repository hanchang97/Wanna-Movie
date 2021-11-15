package com.example.wannamovie.presentation.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.databinding.ActivityWelcomeBinding

class WelcomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}