package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.AdminApi
import com.example.wannamovie.data.remote.HomeApi
import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieWrapper
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieWrapper
import com.example.wannamovie.data.remote.dto.response.home.MovieWrapper
import com.example.wannamovie.domain.repository.AdminRepository
import com.example.wannamovie.domain.repository.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class AdminRepositoryImpl(
        private val adminApi: AdminApi
) : AdminRepository {


    // keyword 기준 요청 수 높은 영화 가져오기
    override fun getAdminMovieList(userToken: String, top_count: Int, keyword: String, keyword_value: String): Single<Response<AdminMovieWrapper>> =
            adminApi.getAdminMovieList(userToken, top_count, keyword, keyword_value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 영화 재개봉 확정하기
    override fun reopenMovie(userToken: String, movieIdDto: AdminReopenMovieRequestDto): Single<Response<Void>> =
            adminApi.reOpenMovie(userToken, movieIdDto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 재개봉 리스트 가져오기
    override fun getReopenMovieList(): Single<Response<ReopenMovieWrapper>> =
            adminApi.getReopenList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}