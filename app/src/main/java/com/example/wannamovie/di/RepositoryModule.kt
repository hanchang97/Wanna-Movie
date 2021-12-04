package com.example.wannamovie.di

import com.example.wannamovie.data.repository.AdminRepositoryImpl
import com.example.wannamovie.data.repository.HomeRepositoryImpl
import com.example.wannamovie.data.repository.SearchRepositoryImpl
import com.example.wannamovie.data.repository.UserRepositoryImpl
import com.example.wannamovie.domain.repository.AdminRepository
import com.example.wannamovie.domain.repository.HomeRepository
import com.example.wannamovie.domain.repository.SearchRepository
import com.example.wannamovie.domain.repository.UserRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<AdminRepository> { AdminRepositoryImpl(get()) }

}