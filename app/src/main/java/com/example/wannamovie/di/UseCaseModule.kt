package com.example.wannamovie.di

import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { UserEmailCheckUseCase(get()) }
}