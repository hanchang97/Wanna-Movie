package com.example.wannamovie.data.repository

import com.example.wannamovie.data.remote.SearchApi
import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.data.remote.dto.response.search.MovieDetailResponseDto
import com.example.wannamovie.data.remote.dto.response.search.SearchRequestMovieResponseDto
import com.example.wannamovie.domain.repository.SearchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SearchRepositoryImpl(
        private val searchApi: SearchApi
) : SearchRepository {

    // 영화 상세 정보 조회
    override fun getMovieDetailInfo(userToken: String, movieId: Int): Single<Response<MovieDetailResponseDto>> =
            searchApi.getMovieDetailInfo(userToken, movieId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 재개봉 요청
    override fun requestMovie(userToken: String, requestMovieResquestDto: SearchRequestMovieResquestDto): Single<Response<Void>> =
            searchApi.requestMovie(userToken, requestMovieResquestDto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    // 댓글 작성
    override fun writeComment(userToken: String, requestData: WriteCommentResquestDto): Single<Response<Void>> =
            searchApi.writeComment(userToken, requestData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


    // 상세정보 열람 영화 조회수 증가
    override fun increaseMovieVisit(movieId: Int): Single<Response<Void>> =
            searchApi.increaseMovieVisit(movieId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}