package com.example.wannamovie.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.wannamovie.databinding.ActivityLoginBinding
import com.example.wannamovie.presentation.signup.SignupActivity


class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()

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
                }
                else{
                    viewModel.inputId(false)
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
                }
                else{
                    viewModel.inputPw(false)
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
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}