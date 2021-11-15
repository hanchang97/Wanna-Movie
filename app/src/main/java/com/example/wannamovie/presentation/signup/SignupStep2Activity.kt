package com.example.wannamovie.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivitySignup2Binding
import com.example.wannamovie.presentation.welcome.WelcomeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SignupStep2Activity: AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    private val viewModel : SignUpStep2ViewModel by viewModel()

    private var Gender = ""
    private var Profession = ""
    private var Address = ""

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

        // intent 값 받기
        Gender = intent.getStringExtra("Gender").toString()
        Address = intent.getStringExtra("Address").toString()
        Profession = intent.getStringExtra("Profession").toString()
        Log.e("AppTest", "step1 -> step2 데이터 전달 체크  성별 : ${Gender}  지역 : ${Address}  직업 : ${Profession}")

        ////////////////////////
        // 이메일 중복확인
        binding.cvEmailCheck.setOnClickListener {
            var email = binding.etId.text.toString()

            if(email.length > 0) {
                viewModel.userEmailCheck(email)
                Log.e("AppTest", "중복확인 시작")
            }
            else
                Toast.makeText(this, "아이디 입력 후 중복확인을 해주세요", Toast.LENGTH_SHORT).show()
        }

        ///////////////
        // 이름 입력



        /////////////////////
        // 나이 입력


        /////////////
        // 비밀번호 입력


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