package com.example.wannamovie.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase

class SignUpStep1ViewModel(
): ViewModel() {

    var btnActive = MutableLiveData<Boolean>()

    private var isGenderSelected = false
    private var isAddressFilled = false
    private var isProfessionSelected = false


    fun selectGender(){
        isGenderSelected = true
        activateBtn()
    }

    fun fillAddress(filled : Boolean){
        isAddressFilled = filled
        activateBtn()
    }

    fun selectProfession(selected : Boolean){
        isProfessionSelected = selected
        activateBtn()
    }


    fun activateBtn() {
        if (isGenderSelected && isAddressFilled && isProfessionSelected)
            btnActive.value = true
        else
            btnActive.value = false
    }
}