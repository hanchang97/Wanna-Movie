package com.example.wannamovie.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import com.example.wannamovie.R
import com.example.wannamovie.common.util.LinearGradientSpan
import com.example.wannamovie.databinding.ActivitySplashBinding
import com.example.wannamovie.presentation.login.LoginActivity

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // textview gradient
        val start = ContextCompat.getColor(this, R.color.splash_title_start)
        val end = ContextCompat.getColor(this, R.color.splash_title_end)

        val titleText = "WANNA MOVIE"
        val spannable = titleText.toSpannable()
        spannable[0..titleText.length] = LinearGradientSpan(titleText, titleText, start, end)
        binding.tvTitle.text = spannable

        startLoading()
    }

    fun startLoading(){
       Handler(Looper.getMainLooper()).postDelayed({
           val intent = Intent(this, LoginActivity::class.java)  // 로그인 화면으로 이동
           startActivity(intent)
           finish()  // 스플래쉬 화면은 최초 실행 시 보여줘야 하므로 finish 해주기!!
       }, 2500)
    }
}