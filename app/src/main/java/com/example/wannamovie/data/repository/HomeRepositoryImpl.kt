package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.HomeApi
import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import com.example.wannamovie.domain.repository.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class HomeRepositoryImpl(
        private val homeApi: HomeApi
) : HomeRepository {


    override fun getHomeMovieList(userToken: String, top_count: Int, keyword: String): Single<Response<MovieWrapper>> =
            homeApi.getHomeMovieList(userToken, top_count, keyword)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}