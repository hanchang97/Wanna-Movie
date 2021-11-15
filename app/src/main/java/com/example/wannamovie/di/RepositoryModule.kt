package com.example.wannamovie.di

import com.example.wannamovie.data.repository.UserRepositoryImpl
import com.example.wannamovie.domain.repository.UserRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }

}