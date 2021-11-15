package com.example.wannamovie.domain.usecase.user

import com.example.wannamovie.domain.repository.UserRepository

class UserEmailCheckUseCase(
        private val repository: UserRepository
) {
    fun getUserEmailCheckResult(email: String)
     = repository.userEmailCheck(email)
}