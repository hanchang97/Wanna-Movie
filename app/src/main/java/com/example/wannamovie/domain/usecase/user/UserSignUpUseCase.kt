package com.example.wannamovie.domain.usecase.user

import com.example.wannamovie.data.remote.dto.request.user.UserSignUpDto
import com.example.wannamovie.domain.repository.UserRepository

class UserSignUpUseCase(
        private val repository: UserRepository
) {
    fun userSignUp(userSignUpDto: UserSignUpDto)
     = repository.userSignUp(userSignUpDto)

}