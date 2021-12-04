package com.example.wannamovie.presentation.main.tab.mypage.reopenlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.domain.usecase.admin.GetReopenMovieListUseCase

class ViewReopenViewModel(
        private val getReopenMovieListUseCase: GetReopenMovieListUseCase
) : ViewModel() {

    var ReopenMovieList = ArrayList<ReopenMovieListResponseDto>()

    var progressbarVisibility = MutableLiveData<Boolean>()
    var IsGetReopenMovieListSuccess = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    fun getReopenMovieList(){
        progressbarVisibility.value = true

        getReopenMovieListUseCase.getReopenMovieList().subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","ViewReopenViewModel/ 재개봉 확정 리스트 조회 성공")

                        it.body()!!.movies.forEach {
                            Log.e("AppTest", "영화 제목 : ${it.title}")

                            ReopenMovieList.add(it)
                        }

                        IsGetReopenMovieListSuccess.value = true
                    }
                    else {
                        Log.e("AppTest","ViewReopenViewModel/ 재개봉 확정 리스트 조회 실패")
                        IsGetReopenMovieListSuccess.value = false
                    }


                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","ViewReopenViewModel/ 재개봉 확정 리스트 조회 오류")
                    IsGetReopenMovieListSuccess.value = false
                    progressbarVisibility.value = false
                }
        )

    }
}