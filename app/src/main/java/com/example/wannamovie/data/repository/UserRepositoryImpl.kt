package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.UserApi
import com.example.wannamovie.data.remote.dto.UserEmailCheckDto
import com.example.wannamovie.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(
        private val userApi: UserApi
) : UserRepository {

    override fun userEmailCheck(email: String): Single<UserEmailCheckDto> =
        userApi.getEmailCheckResult(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

}