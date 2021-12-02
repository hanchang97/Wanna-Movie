package com.example.wannamovie.di

import com.example.wannamovie.domain.usecase.home.HomeGetMovieListUseCase
import com.example.wannamovie.domain.usecase.user.UserEmailCheckUseCase
import com.example.wannamovie.domain.usecase.user.UserGetInfoUseCase
import com.example.wannamovie.domain.usecase.user.UserLoginUseCase
import com.example.wannamovie.domain.usecase.user.UserSignUpUseCase
import org.koin.dsl.module

internal val useCaseModule = module {

    factory { UserEmailCheckUseCase(get()) }
    factory { UserSignUpUseCase(get()) }
    factory { UserLoginUseCase(get()) }
    factory { UserGetInfoUseCase(get())}

    factory { HomeGetMovieListUseCase(get()) }
}