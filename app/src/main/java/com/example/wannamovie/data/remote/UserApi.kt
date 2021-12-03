package com.example.wannamovie.data.remote

import com.example.wannamovie.data.remote.dto.request.user.UserLoginRequestDto
import com.example.wannamovie.data.remote.dto.response.user.UserEmailCheckDto
import com.example.wannamovie.data.remote.dto.request.user.UserSignUpDto
import com.example.wannamovie.data.remote.dto.response.user.UserInfoResponseDto
import com.example.wannamovie.data.remote.dto.response.user.UserLoginResponseDto
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

    //로그인
    @PUT("user")
    fun userLogin(
        @Body userdata: UserLoginRequestDto
    ):Single<Response<UserLoginResponseDto>>

    //유저정보 조회
    @GET("user/info")
    fun getUserInfo(
            @Header("access-token") userToken: String
    ): Single<Response<UserInfoResponseDto>>

}