package com.example.wannamovie.domain.usecase.user

import com.example.wannamovie.data.remote.dto.request.user.UserLoginRequestDto
import com.example.wannamovie.domain.repository.UserRepository

class UserLoginUseCase(
        private val repository: UserRepository
) {
    fun userLogin(userLoginRequestDto: UserLoginRequestDto)
     = repository.userLogin(userLoginRequestDto)

}