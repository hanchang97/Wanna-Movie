package com.example.wannamovie.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.UserSignUpDto
import com.example.wannamovie.databinding.ActivitySignup2Binding
import com.example.wannamovie.presentation.welcome.WelcomeActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SignupStep2Activity: AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    private val viewModel : SignUpStep2ViewModel by viewModel()

    private var Gender = ""
    private var Profession = ""
    private var Address = ""
    private var Name = ""
    private var Age = 0
    private var Id = ""
    private var Pw = ""
    private var PwCheck = ""

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

        viewModel.userEmailCheckedLD.observe(this, {
            if(!it){ // 중복x
                Toast.makeText(this, "사용 가능한 아이디 입니다", Toast.LENGTH_SHORT).show()
                Id = binding.etId.text.toString()
            }
            else{
                Toast.makeText(this, "이미 사용중인 아이디 입니다", Toast.LENGTH_SHORT).show()
                Id = binding.etId.text.toString()
            }
        })

        /////////////////////////////////
        // 이름 입력
        binding.etName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var nameText = binding.etName.text

                if(nameText!!.length > 0){
                    viewModel.fillName(true)
                    Name = binding.etName.text.toString()
                }
                else{
                    viewModel.fillName(false)
                    Address = binding.etName.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



        /////////////////////
        // 나이 입력
        binding.etAge.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var ageText = binding.etAge.text

                if(ageText!!.length > 0){
                    viewModel.fillAge(true)
                    Age = binding.etAge.text.toString().toInt()
                }
                else{
                    viewModel.fillAge(false)
                    Age = 0
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


        /////////////
        // 비밀번호 입력
        binding.etPw.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwText = binding.etPw.text

                if(pwText!!.length > 0){
                    viewModel.fillPw(true)
                    Pw = binding.etPw.text.toString()
                }
                else{
                    viewModel.fillPw(false)
                    Pw = binding.etPw.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        ///////////////////////////////////////////////
        // 비밀번호 확인
        binding.etPwCheck.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var pwCheckText = binding.etPwCheck.text

                if(pwCheckText!!.length > 0){
                    if(pwCheckText.toString().equals(binding.etPw.text.toString())){
                        viewModel.fillPwCheck(true)
                    }
                    else{
                        viewModel.fillPwCheck(false)
                    }
                    PwCheck = binding.etPwCheck.text.toString()
                }
                else{
                    viewModel.fillPwCheck(false)
                    PwCheck = binding.etPwCheck.text.toString()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        ///////////////////////////////
        // 모든 정보 정상적으로 입력 시 가입완료 버튼 활성화
        viewModel.btnActive.observe(this, {
            if(it){
                binding.btnSignupCompleteActive.visibility = View.VISIBLE
                binding.btnSignupCompleteInactive.visibility = View.INVISIBLE
            }
            else{
                binding.btnSignupCompleteActive.visibility = View.INVISIBLE
                binding.btnSignupCompleteInactive.visibility = View.VISIBLE
            }
        })

        // 가입정보 모두 입력 후 버튼 클릭
        binding.btnSignupCompleteActive.setOnClickListener {
            /*val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)*/
            Log.e("AppTest", "가입완료 정보 확인 gender:${Gender} address:${Address} profession:${Profession} " +
                    "email:${Id} name:${Name} age:${Age} pw:${Pw} pwCheck:${PwCheck}")

            var userSignUpDto = UserSignUpDto(Id, Name, Age, Profession, Address, Gender, Pw, PwCheck)
            viewModel.userSignUp(userSignUpDto)
        }

        // 회원가입 성공 시
        viewModel.signUpSuccess.observe(this, {
            Log.e("AppTest","signup success & go to welcome activity")
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        })

        // loading progressbar
        viewModel.progressBarVisibility.observe(this, {
            if(it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.INVISIBLE
        })
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