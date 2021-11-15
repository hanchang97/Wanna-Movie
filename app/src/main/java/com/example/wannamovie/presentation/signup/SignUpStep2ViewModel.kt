package com.example.wannamovie.presentation.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase

class SignUpStep2ViewModel(
        private val getUserEmailCheckResultUseCase: UserEmailCheckUseCase
): ViewModel() {

    var btnActive = MutableLiveData<Boolean>()  // 모든 정보 기입 완료 시 가입완료 버튼 활성화

    var isUserEmailExist = true  // false 여야 가입완료
    var isNameFilled = false
    var isAgeFilled = false
    var isPwFilled = false
    var isPwCheckFilled = false




    fun userEmailCheck(email: String){

        getUserEmailCheckResultUseCase.getUserEmailCheckResult(email).subscribe(
                {
                    var isExist = it.isExist
                    isUserEmailExist = isExist
                    Log.e("AppTest", "중복체크 결과 isExist = ${isExist}")
                },
                {
                    throwable ->
                    Log.e("AppTest","이메일 중복 체크 api 오류")
                }
        )
        btnActivateCheck()
    }

    fun btnActivateCheck(){
        if(!isUserEmailExist && isNameFilled && isAgeFilled && isPwFilled && isPwCheckFilled)
            btnActive.value = true
        else
            btnActive.value = false
    }
}