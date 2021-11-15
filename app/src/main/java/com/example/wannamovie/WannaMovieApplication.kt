package com.example.wannamovie

import android.app.Application
import com.example.wannamovie.di.remoteModule
import com.example.wannamovie.di.repositoryModule
import com.example.wannamovie.di.useCaseModule
import com.example.wannamovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WannaMovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // koin 사용
        startKoin {
            androidContext(this@WannaMovieApplication)

            modules(remoteModule)
            modules(repositoryModule)
            modules(useCaseModule)
            modules(viewModelModule)
        }

    }
}