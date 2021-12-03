package com.example.wannamovie.di

import com.example.wannamovie.domain.usecase.home.HomeGetMovieListUseCase
import com.example.wannamovie.domain.usecase.search.IncreaseMovieVisitUseCase
import com.example.wannamovie.domain.usecase.search.RequestMovieUseCase
import com.example.wannamovie.domain.usecase.search.SearchMovieDetailUseCase
import com.example.wannamovie.domain.usecase.search.WriteCommentUseCase
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

    factory { SearchMovieDetailUseCase(get()) }
    factory { RequestMovieUseCase(get()) }
    factory { WriteCommentUseCase(get()) }
    factory { IncreaseMovieVisitUseCase(get()) }
}