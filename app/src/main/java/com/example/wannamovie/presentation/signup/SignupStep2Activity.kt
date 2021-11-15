package com.example.wannamovie.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivitySignup2Binding
import com.example.wannamovie.presentation.welcome.WelcomeActivity

class SignupStep2Activity: AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 기본제목 없애기
        //supportActionBar!!.setTitle("회원가입")


        // 추후에 active 버튼 클릭 시로 변경하기 / 현재 발표용으로 우선 넘어가게 설정
        binding.btnSignupCompleteInactive.setOnClickListener {
            Log.e("AppTest","go to welcome activity")
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }


    }

    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}