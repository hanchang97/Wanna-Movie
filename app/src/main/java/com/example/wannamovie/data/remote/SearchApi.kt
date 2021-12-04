package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import com.example.wannamovie.data.remote.dto.response.search.MovieDetailResponseDto
import com.example.wannamovie.data.remote.dto.response.search.SearchMovie
import com.example.wannamovie.data.remote.dto.response.search.SearchRequestMovieResponseDto
import com.example.wannamovie.data.remote.dto.response.user.UserInfoResponseDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface SearchApi {

    // 영화 상세정보 조회
    @GET("movie/{movie_id}")
    fun getMovieDetailInfo(
            @Header("access-token") userToken: String,
            @Path("movie_id") movie_id: Int,
            ): Single<Response<MovieDetailResponseDto>>

    // 영화 재개봉 요청
    @POST("request")
    fun requestMovie(
            @Header("access-token") userToken: String,
            @Body requestData : SearchRequestMovieResquestDto
    ) : Single<Response<Void>>


    // 댓글 작성하기
    @POST("comments")
    fun writeComment(
            @Header("access-token") userToken: String,
            @Body requestData : WriteCommentResquestDto
    ) : Single<Response<Void>>

    // 영화 조회수 증가
    @PUT("movie/{movie_id}")
    fun increaseMovieVisit(
            @Path("movie_id") movie_id: Int
    ) : Single<Response<Void>>


    // 영화 검색
    @GET("movie-list")
    fun searchMovie(
            @QueryMap keyWordMap: Map<String, String>,
            @QueryMap pageMap : Map<String, Int>
    ) : Single<Response<SearchMovie>>




}