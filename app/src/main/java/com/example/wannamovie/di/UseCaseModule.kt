package com.example.wannamovie.di

import com.example.wannamovie.domain.usecase.admin.AdminGetMovieListUseCase
import com.example.wannamovie.domain.usecase.admin.AdminReopenMovieUseCase
import com.example.wannamovie.domain.usecase.admin.GetReopenMovieListUseCase
import com.example.wannamovie.domain.usecase.home.HomeGetMovieListUseCase
import com.example.wannamovie.domain.usecase.search.*
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
    factory { SearchMovieUseCase(get()) }

    factory { AdminGetMovieListUseCase(get()) }
    factory { AdminReopenMovieUseCase(get()) }

    factory { GetReopenMovieListUseCase(get()) }
    factory { GetMyRequestListUseCase(get()) }
}