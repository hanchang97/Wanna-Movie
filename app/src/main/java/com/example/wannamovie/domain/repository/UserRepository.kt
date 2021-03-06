package com.example.wannamovie.domain.repository

import com.example.wannamovie.data.remote.dto.request.user.UserLoginRequestDto
import com.example.wannamovie.data.remote.dto.response.user.UserEmailCheckDto
import com.example.wannamovie.data.remote.dto.request.user.UserSignUpDto
import com.example.wannamovie.data.remote.dto.response.user.UserInfoResponseDto
import com.example.wannamovie.data.remote.dto.response.user.UserLoginResponseDto
import io.reactivex.Single
import retrofit2.Response

interface UserRepository {
    fun userEmailCheck(email: String) : Single<UserEmailCheckDto>
    fun userSignUp(userSignUpDto: UserSignUpDto): Single<Response<Void>>
    fun userLogin(userLoginRequestDto: UserLoginRequestDto) : Single<Response<UserLoginResponseDto>>

    fun getUserInfo(userToken: String) : Single<Response<UserInfoResponseDto>>
}



/**
 * domain 에 repository interface 를 두는 것의 장점
 * 1. Test 에 용이 (fake 구현체만 바꿔서 주입하면 되므로)
 * 2. Domain 만 봐도 이 앱이 어떤 비즈니스 로직을 수행하는지 한눈에 알아볼 수 있음(?)
 * 레퍼런스 더 찾아보기!!
 */