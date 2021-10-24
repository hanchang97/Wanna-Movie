package com.example.wannamovie.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var btnActive = MutableLiveData<Boolean>()
    private var isIdEmpty = true
    private var isPwEmpty = true



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
}