package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieWrapper
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieWrapper
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface AdminApi {

    // 관리자가 영화 리스트 조회
    @GET("admin/statistics")
    fun getAdminMovieList(
            @Header("access-token") userToken: String,
            @Query("top_count") top_count: Int,
            @Query("keyword") keywordType: String,
            @Query("keyword_value") keyword_value: String
    ) : Single<Response<AdminMovieWrapper>>


    // 재개봉 확정하기
    @POST("admin/re-open")
    fun reOpenMovie(
            @Header("access-token") userToken: String,
            @Body movieIdDto : AdminReopenMovieRequestDto
    ) : Single<Response<Void>>


    // 재개봉 확정 리스트 가져오기
    @GET("admin/re-open/list")
    fun getReopenList() : Single<Response<ReopenMovieWrapper>>
}