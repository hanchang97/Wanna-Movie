package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.UserEmailCheckDto
import com.example.wannamovie.data.remote.dto.UserSignUpDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    // 이메일 중복 체크
    @GET("user")
    fun getEmailCheckResult(
        @Query("email") email: String
    ): Single<UserEmailCheckDto>
    
    // 회원가입
    @POST("user")
    fun userSignUp(
        @Body userdata: UserSignUpDto
    ):Single<Response<Void>>
}