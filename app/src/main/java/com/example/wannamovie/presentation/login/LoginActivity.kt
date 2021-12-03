package com.example.wannamovie.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.databinding.ActivityLoginBinding
import com.example.wannamovie.domain.repository.UserRepository
import com.example.wannamovie.presentation.main.MainActivity
import com.example.wannamovie.presentation.signup.SignUpStep1Activity
import org.koin.android.viewmodel.ext.android.viewModel



class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etId.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var idText = binding.etId.text

                if(idText!!.length > 0){
                    viewModel.inputId(true)
                    viewModel.ID = idText.toString()
                }
                else{
                    viewModel.inputId(false)
                    viewModel.ID = ""
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.etPw.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwText = binding.etPw.text

                if(pwText!!.length > 0){
                    viewModel.inputPw(true)
                    viewModel.PW = pwText.toString()
                }
                else{
                    viewModel.inputPw(false)
                    viewModel.PW = ""
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //////////////////////////////////////////////////////////

        // 버튼 활성화 감지
        viewModel.btnActive.observe(this, {
            if(it){
                binding.btnLoginActive.visibility = View.VISIBLE
                binding.btnLoginInactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnLoginActive.visibility = View.INVISIBLE
                binding.btnLoginInactive.visibility = View.VISIBLE
            }
        })


        // 회원가입 이동
        binding.tvGotoSignup.setOnClickListener {
            Log.e("AppTest","go to signup activity")
            val intent = Intent(this, SignUpStep1Activity::class.java)
            startActivity(intent)
        }


        // 로그인 버튼 누를 시 ->
        binding.btnLoginActive.setOnClickListener {
            viewModel.logIn()
           /* val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/
        }

        // 로딩 바
        viewModel.progressBarVisibility.observe(this, {
            if(it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.INVISIBLE
        })

        // 로그인 성공 시 처리
        viewModel.Login_Success.observe(this, {
            if(it){
                if(Constants2.USER_ROLE.equals("user")){  // 일반 유저 페이지 이동
                    Log.e("AppTest","role : ${Constants2.USER_ROLE}/go to main activity")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    // 관리자 페이지 이동
                    Log.e("AppTest","role : ${Constants2.USER_ROLE}/ go to admin activity")

                }
            }
            else{
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.etId.clearFocus()
        binding.etPw.clearFocus()
    }
}