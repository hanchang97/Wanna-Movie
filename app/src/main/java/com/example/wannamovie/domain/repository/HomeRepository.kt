package com.example.wannamovie.domain.repository

import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import io.reactivex.Single
import retrofit2.Response

interface HomeRepository {

    // 홈 영화 리스트 조회
    fun getHomeMovieList(userToken: String, top_count: Int, keyword: String) : Single<Response<MovieWrapper>>

}

