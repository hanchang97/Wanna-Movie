package com.example.wannamovie.di

import com.example.wannamovie.presentation.login.LoginViewModel
import com.example.wannamovie.presentation.main.MainActivityViewModel
import com.example.wannamovie.presentation.signup.SignUpStep1ViewModel
import com.example.wannamovie.presentation.signup.SignUpStep2ViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { SignUpStep2ViewModel(get(), get()) }
    viewModel { SignUpStep1ViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { MainActivityViewModel() }
}