package com.example.wannamovie.di

import com.example.wannamovie.presentation.admin.AdminViewModel
import com.example.wannamovie.presentation.login.LoginViewModel
import com.example.wannamovie.presentation.main.MainActivityViewModel
import com.example.wannamovie.presentation.main.tab.home.HomeViewModel
import com.example.wannamovie.presentation.main.tab.mypage.myrequest.MyRequestListViewModel
import com.example.wannamovie.presentation.main.tab.mypage.reopenlist.ViewReopenViewModel
import com.example.wannamovie.presentation.main.tab.search.SearchViewModel
import com.example.wannamovie.presentation.moviedetail.MovieDetailViewModel
import com.example.wannamovie.presentation.moviedetail.commentpage.MovieCommentViewModel
import com.example.wannamovie.presentation.signup.SignUpStep1ViewModel
import com.example.wannamovie.presentation.signup.SignUpStep2ViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel { SignUpStep2ViewModel(get(), get()) }
    viewModel { SignUpStep1ViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { MainActivityViewModel() }
    viewModel { HomeViewModel(get(), get())}
    viewModel { MovieDetailViewModel(get(), get(), get()) }
    viewModel { MovieCommentViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }

    viewModel { AdminViewModel(get(), get()) }
    viewModel { ViewReopenViewModel(get()) }
    viewModel { MyRequestListViewModel(get()) }

}