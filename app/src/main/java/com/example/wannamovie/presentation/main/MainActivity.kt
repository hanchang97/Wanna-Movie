package com.example.wannamovie.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","main activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)






    }
}