package com.example.wannamovie.domain.repository

import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.data.remote.dto.response.search.MovieDetailResponseDto
import com.example.wannamovie.data.remote.dto.response.search.SearchRequestMovieResponseDto
import io.reactivex.Single
import retrofit2.Response

interface SearchRepository {

    // 영화 상세정보 조회
    fun getMovieDetailInfo(userToken: String, movieId: Int) : Single<Response<MovieDetailResponseDto>>

    // 영화 재개봉 요청하기
    fun requestMovie(userToken: String, requestMovieResquestDto: SearchRequestMovieResquestDto) : Single<Response<Void>>

    // 영화 댓글 작성하기
    fun writeComment(userToken: String, requestData: WriteCommentResquestDto) : Single<Response<Void>>

    // 영화 상세 정보 열람 시 해당 영화 조회수 증가
    fun increaseMovieVisit(movieId: Int) : Single<Response<Void>>

}

