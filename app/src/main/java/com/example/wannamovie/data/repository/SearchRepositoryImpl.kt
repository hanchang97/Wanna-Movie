package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.SearchApi
import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.data.remote.dto.response.search.MovieDetailResponseDto
import com.example.wannamovie.data.remote.dto.response.search.SearchRequestMovieResponseDto
import com.example.wannamovie.domain.repository.SearchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SearchRepositoryImpl(
        private val searchApi: SearchApi
) : SearchRepository {


    override fun getMovieDetailInfo(userToken: String, movieId: Int): Single<Response<MovieDetailResponseDto>> =
            searchApi.getMovieDetailInfo(userToken, movieId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    override fun requestMovie(userToken: String, requestMovieResquestDto: SearchRequestMovieResquestDto): Single<Response<Void>> =
            searchApi.requestMovie(userToken, requestMovieResquestDto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())



}