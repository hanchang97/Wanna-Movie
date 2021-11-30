package com.example.wannamovie.presentation.signup

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.data.remote.dto.request.UserSignUpDto
import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase
import com.example.wannamovie.domain.usecase.user.UserSignUpUseCase

class SignUpStep2ViewModel(
        private val getUserEmailCheckResultUseCase: UserEmailCheckUseCase,
        private val userSignUpUseCase: UserSignUpUseCase
): ViewModel() {

    var btnActive = MutableLiveData<Boolean>()  // 모든 정보 기입 완료 시 가입완료 버튼 활성화
    var userEmailCheckedLD = MutableLiveData<Boolean>() // 유저 이메일 중복체크 되었는지 토스트 메시지 띄우기 위한 라이브데이터
    var signUpSuccess = MutableLiveData<Boolean>()
    var progressBarVisibility = MutableLiveData<Boolean>()

    var isUserEmailExist = true  // false 여야 가입완료
    var isNameFilled = false
    var isAgeFilled = false
    var isPwFilled = false
    var isPwCheckFilled = false




    @SuppressLint("CheckResult")
    fun userEmailCheck(email: String){
        progressBarVisibility.value = true
        getUserEmailCheckResultUseCase.getUserEmailCheckResult(email).subscribe(
                {
                    var isExist = it.isExist
                    isUserEmailExist = isExist
                    Log.e("AppTest", "중복체크 결과 isExist = ${isExist}")
                    userEmailCheckedLD.value = isExist
                    btnActivateCheck()
                    progressBarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","이메일 중복 체크 api 오류")
                    progressBarVisibility.value = false
                }
        )
        btnActivateCheck()
    }

    fun fillName(filled: Boolean){
        isNameFilled = filled
        btnActivateCheck()
    }

    fun fillAge(filled: Boolean){
        isAgeFilled = filled
        btnActivateCheck()
    }

    fun fillPw(filled: Boolean){
        isPwFilled = filled
        btnActivateCheck()
    }

    fun fillPwCheck(filled: Boolean){
        isPwCheckFilled = filled
        btnActivateCheck()
    }


    fun btnActivateCheck(){
        if(!isUserEmailExist && isNameFilled && isAgeFilled && isPwFilled && isPwCheckFilled){
            btnActive.value = true
        }
        else
            btnActive.value = false
    }

    // 회원가입
    @SuppressLint("CheckResult")
    fun userSignUp(userSignUpDto: UserSignUpDto){
        progressBarVisibility.value = true
        userSignUpUseCase.userSignUp(userSignUpDto).subscribe(
                {
                    progressBarVisibility.value = false
                    Log.e("AppTest", "user signup result status code : ${it.code()}")
                    if(it.code() == 201){ // 회원 가입 성공
                        signUpSuccess.value = true
                    }
                },
                {
                    throwable -> Log.e("AppTest", "sign up error ${throwable}")
                    progressBarVisibility.value = false
                }
        )
    }
}