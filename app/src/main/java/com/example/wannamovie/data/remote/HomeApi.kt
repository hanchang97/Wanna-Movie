package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import com.example.wannamovie.data.remote.dto.response.user.UserInfoResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi {

    //홈 속성별 top5 조회 / keyword = age  or  address  or  sex
    @GET("home")
    fun getHomeMovieList(
            @Header("access-token") userToken: String,
            @Query("top_count") top_count: Int,
            @Query("keyword") keyword: String
    ): Single<Response<MovieWrapper>>

}