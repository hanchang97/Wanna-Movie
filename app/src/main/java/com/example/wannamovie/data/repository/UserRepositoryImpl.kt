package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.UserApi
import com.example.wannamovie.data.remote.dto.request.user.UserLoginRequestDto
import com.example.wannamovie.data.remote.dto.response.user.UserEmailCheckDto
import com.example.wannamovie.data.remote.dto.request.user.UserSignUpDto
import com.example.wannamovie.data.remote.dto.response.user.UserInfoResponseDto
import com.example.wannamovie.data.remote.dto.response.user.UserLoginResponseDto
import com.example.wannamovie.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class UserRepositoryImpl(
        private val userApi: UserApi
) : UserRepository {

    override fun userEmailCheck(email: String): Single<UserEmailCheckDto> =
        userApi.getEmailCheckResult(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun userSignUp(userSignUpDto: UserSignUpDto): Single<Response<Void>> =
        userApi.userSignUp(userSignUpDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun userLogin(userLoginRequestDto: UserLoginRequestDto): Single<Response<UserLoginResponseDto>> =
        userApi.userLogin(userLoginRequestDto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    // 유저 정보 조회
    override fun getUserInfo(userToken: String): Single<Response<UserInfoResponseDto>> =
            userApi.getUserInfo(userToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())



}