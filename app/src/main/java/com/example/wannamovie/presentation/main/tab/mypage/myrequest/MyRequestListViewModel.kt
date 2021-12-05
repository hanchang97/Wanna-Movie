package com.example.wannamovie.presentation.main.tab.mypage.myrequest

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.search.MyRequestListResponseDto
import com.example.wannamovie.domain.usecase.search.GetMyRequestListUseCase

class MyRequestListViewModel(
        private val getMyRequestListUseCase: GetMyRequestListUseCase
): ViewModel() {

    var MyRequestMovieList = ArrayList<MyRequestListResponseDto>()

    var progressbarVisibility = MutableLiveData<Boolean>()
    var IsGetMyRequestMovieListSuccess = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getMyRequestMovieList(){
        progressbarVisibility.value = true
        MyRequestMovieList.clear()

        getMyRequestListUseCase.getMyRequestList(Constants.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","MyRequestListViewModel/ 나의 요청 리스트 조회 성공")

                        it.body()!!.movies.forEach {
                            Log.e("AppTest", "영화 제목 : ${it.title}")

                            MyRequestMovieList.add(it)
                        }

                        IsGetMyRequestMovieListSuccess.value = true
                    }
                    else {
                        Log.e("AppTest","MyRequestListViewModel/ 나의 요청 리스트 조회 실패")
                        IsGetMyRequestMovieListSuccess.value = false
                    }


                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","MyRequestListViewModel/ 나의 요청 리스트 조회 오류")
                    IsGetMyRequestMovieListSuccess.value = false
                    progressbarVisibility.value = false
                }
        )

    }
}