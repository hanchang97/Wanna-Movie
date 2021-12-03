package com.example.wannamovie.presentation.moviedetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.domain.usecase.search.IncreaseMovieVisitUseCase
import com.example.wannamovie.domain.usecase.search.RequestMovieUseCase
import com.example.wannamovie.domain.usecase.search.SearchMovieDetailUseCase

class MovieDetailViewModel(
        private val searchMovieDetailUseCase: SearchMovieDetailUseCase,
        private val requestMovieUseCase: RequestMovieUseCase,
        private val increaseMovieVisitUseCase: IncreaseMovieVisitUseCase
): ViewModel() {

    var IsGetMovieDetailSuccess = MutableLiveData<Boolean>()
    var progressbarVisbility = MutableLiveData<Boolean>()

    var IsRequestMovieSuccess = MutableLiveData<Boolean>()
    var progressbarVisbility_movieRequest = MutableLiveData<Boolean>()

    var Movie_Title = ""
    var Movie_ReleaseDate_Origin = ""
    var Movie_Request_Num = 0
    var Movie_ReleaseDate = ""
    var Is_Request = false
    var Movie_Poster_Url = ""

    @SuppressLint("CheckResult")
    fun getMovieDetailInfo(movieId: Int){
        progressbarVisbility.value = true

        searchMovieDetailUseCase.getMovieDetailInfo(Constants.USER_TOKEN, movieId).subscribe(
                {
                    if(it.code() == 200){
                        Movie_Title = it.body()!!.title // 제목
                        Movie_ReleaseDate_Origin = it.body()!!.release_date // 개봉일
                        Movie_Request_Num = it.body()!!.request_count  // 영화 요청수

                        Movie_ReleaseDate = Movie_ReleaseDate_Origin.substring(0 until 10)  // 날짜문자열 필터링

                        Is_Request = it.body()!!.is_request // 유저가 재개봉 요청했던 것인지
                        Movie_Poster_Url = it.body()!!.poster_path   // 영화 포스터 url

                        Log.e("AppTest", "MovieDetailViewModel/ 영화 상세 정보 조회 성공\n" +
                                "영화제목 : ${Movie_Title}\n" +
                                "개봉일 raw : ${Movie_ReleaseDate_Origin}\n" +
                                "개봉일 : ${Movie_ReleaseDate}\n" +
                                "요청수 : ${Movie_Request_Num}\n" +
                                "요청여부 : ${Is_Request}\n" +
                                "포스터url : ${Movie_Poster_Url}")

                        IsGetMovieDetailSuccess.value = true
                    }
                    else{
                        Log.e("AppTest","MovieDetailViewModel/ 영화 상세 정보 조회 실패")
                        IsGetMovieDetailSuccess.value = false
                    }

                    progressbarVisbility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","MovieDetailViewModel/ 영화 상세 정보 조회 오류")
                    progressbarVisbility.value = false
                    IsGetMovieDetailSuccess.value = false
                }
        )
    }


    // 재개봉 요청
    @SuppressLint("CheckResult")
    fun requestMovie(movie_id: Int, content: String){
        var movieRequestDto = SearchRequestMovieResquestDto(movie_id, content) // request body

        progressbarVisbility_movieRequest.value = true

        requestMovieUseCase.requestMovie(Constants.USER_TOKEN, movieRequestDto).subscribe(
                {
                    if(it.code() == 201){
                        Log.e("AppTest","MovieDetailViewModel/ 영화 재개봉 요청 성공")
                        IsRequestMovieSuccess.value = true

                    }
                    else{
                        Log.e("AppTest","MovieDetailViewModel/ 영화 재개봉 요청 실패")
                        IsRequestMovieSuccess.value = false
                    }

                    progressbarVisbility_movieRequest.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","MovieDetailViewModel/ 영화 재개봉 요청 오류")
                    progressbarVisbility_movieRequest.value = false
                    IsRequestMovieSuccess.value = false
                }
        )

    }


    // 상세정보 열람 영화 조회수 증가
    @SuppressLint("CheckResult")
    fun increaseMovieVisit(movie_id: Int){

        increaseMovieVisitUseCase.increaseMovieVisit(movie_id).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest", "MovieDetailViewModel/ 현재 영화 id : ${movie_id}  조회수 증가 성공")
                    }
                    else{
                        Log.e("AppTest", "MovieDetailViewModel/ 조회수 증가 실패, code : ${it.code()}")
                    }
                },
                {
                    throwable ->
                    Log.e("AppTest","MovieDetailViewModel/ 조회수 증가 오류")
                }
        )
    }
}