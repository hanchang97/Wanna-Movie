package com.example.wannamovie.presentation.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.data.remote.dto.request.user.UserLoginRequestDto
import com.example.wannamovie.domain.usecase.user.UserLoginUseCase

class LoginViewModel(
    private val userLoginUseCase: UserLoginUseCase
): ViewModel() {

    var btnActive = MutableLiveData<Boolean>()
    private var isIdEmpty = true
    private var isPwEmpty = true

    var ID = ""
    var PW = ""

    var progressBarVisibility = MutableLiveData<Boolean>()

    // 로그인 성공 시!
    var Login_Success = MutableLiveData<Boolean>()




    fun inputId(flag: Boolean){
        isIdEmpty = !flag
        idPWCheck()
    }

    fun inputPw(flag: Boolean){
        isPwEmpty = !flag
        idPWCheck()
    }

    fun idPWCheck(){
        if(!isIdEmpty && !isPwEmpty){
            btnActive.value = true
        }
        else{
            btnActive.value = false
        }
    }

    @SuppressLint("CheckResult")
    fun logIn(){
        progressBarVisibility.value = true
        var userLoginRequestDto = UserLoginRequestDto("login", ID, PW)
        userLoginUseCase.userLogin(userLoginRequestDto).subscribe(
            {
                if(it.code() == 200){
                    Log.e("AppTest","로그인 성공")

                    Constants.USER_TOKEN = it.body()!!.token
                    Log.e("AppTest","유저 토큰 : ${Constants.USER_TOKEN}")

                    Login_Success.value = true
                }
                else{
                    Log.e("AppTest","로그인 실패, status code : ${it.code()}")
                    Login_Success.value = false
                }

                progressBarVisibility.value = false
            },
            { throwable ->
                Log.e("AppTest","이메일 중복 체크 api 오류")
                progressBarVisibility.value = false
                Login_Success.value = false
            }
        )
    }
}