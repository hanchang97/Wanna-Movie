package com.example.wannamovie.di

import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase
import com.example.wannamovie.domain.usecase.user.UserSignUpUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { UserEmailCheckUseCase(get()) }
    factory { UserSignUpUseCase(get()) }
}