package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.UserEmailCheckDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UserApi {
    // 이메일 중복 체크
    @GET("user")
    fun getEmailCheckResult(
        @Query("email") email: String
    ): Single<UserEmailCheckDto>
}