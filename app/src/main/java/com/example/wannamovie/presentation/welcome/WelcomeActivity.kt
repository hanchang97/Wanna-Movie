package com.example.wannamovie.presentation.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.databinding.ActivityWelcomeBinding
import com.example.wannamovie.presentation.login.LoginActivity
import com.example.wannamovie.presentation.signup.SignUpStep1Activity

class WelcomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 화면으로 이동
        binding.btnGotoLogin.setOnClickListener {
            Log.e("AppTest","go to login activity")
            val intent = Intent(this, LoginActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



    }
}