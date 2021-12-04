package com.example.wannamovie.domain.repository

import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieWrapper
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieWrapper
import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import io.reactivex.Single
import retrofit2.Response

interface AdminRepository {

    // 관리자 영화 리스트 조회
    fun getAdminMovieList(userToken: String, top_count: Int, keyword: String, keyword_value: String) : Single<Response<AdminMovieWrapper>>

    // 영화 재개봉 확정하기
    fun reopenMovie(userToken: String, movieIdDto: AdminReopenMovieRequestDto) : Single<Response<Void>>

    // 재개봉 리스트 가져오기
    fun getReopenMovieList() : Single<Response<ReopenMovieWrapper>>
}