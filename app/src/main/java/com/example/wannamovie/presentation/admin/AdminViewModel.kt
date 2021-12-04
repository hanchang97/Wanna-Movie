package com.example.wannamovie.presentation.admin

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.domain.entity.MovieSearched
import com.example.wannamovie.domain.usecase.admin.AdminGetMovieListUseCase
import com.example.wannamovie.domain.usecase.admin.AdminReopenMovieUseCase

class AdminViewModel(
        private val getAdminMovieListUseCase : AdminGetMovieListUseCase,
        private val adminReopenMovieUseCase: AdminReopenMovieUseCase
): ViewModel() {

    // 영화 리스트 담기
    var AdminMovieList = ArrayList<AdminMovieListResponseDto>()
    var progressbarVisibility = MutableLiveData<Boolean>()
    var IsGetMovieListSuccess = MutableLiveData<Boolean>()

    var IsReopenMovieSuccess = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getMovieList(top_count: Int, keyword: String, keyword_value: String){
        AdminMovieList.clear()
        progressbarVisibility.value = true

        getAdminMovieListUseCase.getAdminMovieList(Constants.USER_TOKEN, top_count, keyword, keyword_value).subscribe(
                {
                    if(it.code()== 200){
                        Log.e("AppTest", "AdminViewModel/ 영화 검색 성공")
                        it.body()!!.movies.forEach {
                            Log.e("AppTest", "영화 제목 : ${it.title}")

                            AdminMovieList.add(it)
                        }
                        IsGetMovieListSuccess.value = true

                    }
                    else{
                        Log.e("AppTest", "AdminViewModel/ 영화 검색 실패")
                        IsGetMovieListSuccess.value = false
                    }

                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","AdminViewModel/ 영화 검색 오류")
                    IsGetMovieListSuccess.value = false
                    progressbarVisibility.value = false
                }
        )

    }



    @SuppressLint("CheckResult")
    fun reopenMovie(movie_id: Int){
        progressbarVisibility.value = true

        var movieIdDto = AdminReopenMovieRequestDto(movie_id)

        adminReopenMovieUseCase.reopenMovie(Constants.USER_TOKEN, movieIdDto).subscribe(
                {
                    if(it.code() == 201){
                        Log.e("AppTest","AdminViewModel/ 영화 재개봉 확정 성공")
                        IsReopenMovieSuccess.value = true
                    }
                    else{
                        Log.e("AppTest","AdminViewModel/ 영화 재개봉 확정 실패")
                        IsReopenMovieSuccess.value = false
                    }

                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","AdminViewModel/ 영화 재개봉 확정 오류")
                    IsReopenMovieSuccess.value = false
                    progressbarVisibility.value = false
                }
        )

    }
}