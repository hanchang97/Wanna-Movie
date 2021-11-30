package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.UserApi
import com.example.wannamovie.data.remote.dto.request.UserLoginRequestDto
import com.example.wannamovie.data.remote.dto.response.UserEmailCheckDto
import com.example.wannamovie.data.remote.dto.request.UserSignUpDto
import com.example.wannamovie.data.remote.dto.response.UserLoginResponseDto
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


}