package com.example.wannamovie.domain.usecase.user

import com.example.wannamovie.data.remote.dto.request.UserLoginRequestDto
import com.example.wannamovie.domain.repository.UserRepository

class UserGetInfoUseCase(
        private val repository: UserRepository
) {
    fun getUserInfo(userToken: String)
            = repository.getUserInfo(userToken)

}