package com.example.wannamovie.presentation.main.tab.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.domain.usecase.home.HomeGetMovieListUseCase
import com.example.wannamovie.domain.usecase.user.UserGetInfoUseCase

class HomeViewModel(
        private val getUserInfoUseCase: UserGetInfoUseCase,
        private val getHomeMovieListUseCase : HomeGetMovieListUseCase
): ViewModel() {

    var progressbarVisibility_userInfo = MutableLiveData<Boolean>()

    var IsGetUserInfoSuccess = MutableLiveData<Boolean>()

    // 나이별 영화 담기
    var HomeMovieList_Age = ArrayList<HomeMovieResponseDto>()
    var progressbarVisibility_age = MutableLiveData<Boolean>()
    var IsGetMovieListAge = MutableLiveData<Boolean>()
    // 성별
    var HomeMovieList_Sex = ArrayList<HomeMovieResponseDto>()
    var progressbarVisibility_sex = MutableLiveData<Boolean>()
    var IsGetMovieListSex = MutableLiveData<Boolean>()
    // 지역별
    var HomeMovieList_Address = ArrayList<HomeMovieResponseDto>()
    var progressbarVisibility_address = MutableLiveData<Boolean>()
    var IsGetMovieListAddress = MutableLiveData<Boolean>()




    @SuppressLint("CheckResult")
    fun getUserInfo(){
        Log.e("AppTest", "HomeViewModel/ 유저 정보 조회 시작")
        progressbarVisibility_userInfo.value = true

        getUserInfoUseCase.getUserInfo(Constants.USER_TOKEN).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","HomeViewModel/ 유저 정보 조회 성공")

                        Constants2.USER_NAME = it.body()!!.name
                        Constants2.USER_AGE = it.body()!!.age
                        Constants2.USER_ADDRESS = it.body()!!.address
                        Constants2.USER_SEX = it.body()!!.sex
                        Constants2.USER_EMAIL = it.body()!!.email
                        Log.e("AppTest", "HomeViewModel/ 유저정보\n" +
                                "이름 : ${Constants2.USER_NAME}\n" +
                                "나이 : ${Constants2.USER_AGE}\n" +
                                "주소 : ${Constants2.USER_ADDRESS}\n" +
                                "성별 : ${Constants2.USER_SEX}\n" +
                                "이메일 : ${Constants2.USER_EMAIL}\n")

                        IsGetUserInfoSuccess.value = true
                    }
                    else{
                        Log.e("AppTest","HomeViewModel/ 유저 정보 조회 실패, code : ${it.code()}")
                        IsGetUserInfoSuccess.value = false
                    }

                    progressbarVisibility_userInfo.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","HomeViewModel/ 유저 정보 조회 오류")
                    progressbarVisibility_userInfo.value = false
                    IsGetUserInfoSuccess.value = false
                }
        )
    }

    // 홈 영화 조회 - 나이
    @SuppressLint("CheckResult")
    fun getHomeMovieListByAge(){
        //
        progressbarVisibility_age.value = true
        //리스트 비우고 시작!
        HomeMovieList_Age.clear()

        getHomeMovieListUseCase.getHomeMovieList(Constants.USER_TOKEN, 5, "age").subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","HomeViewModel/ 나이 top5 리스트 조회 성공")
                        if(it.body() != null){

                            it.body()!!.movies.forEach{
                                Log.e("AppTest", "나이/ 영화제목 : ${it.title},\n" +
                                        "포스터 경로 : ${it.poster_path}")
                                HomeMovieList_Age.add(it)
                            }

                            IsGetMovieListAge.value = true //
                        }
                        else{
                            Log.e("AppTest","HomeViewModel/ 나이 top5 리스트 empty")
                            IsGetMovieListAge.value = false //
                        }
                    }
                    else{
                        Log.e("AppTest","HomeViewModel/ 나이 top5 리스트 조회 실패, code : ${it.code()}")
                        IsGetMovieListAge.value = false //
                    }

                    progressbarVisibility_age.value = false //
                },
                {
                    throwable ->
                    Log.e("AppTest","HomeViewModel/ 나이 top5 리스트 조회 오류")
                    progressbarVisibility_age.value = false //
                    IsGetMovieListAge.value = false //
                }
        )
    }


    // 홈 영화 조회 - 성별
    @SuppressLint("CheckResult")
    fun getHomeMovieListBySex(){
        //
        progressbarVisibility_sex.value = true
        //리스트 비우고 시작!
        HomeMovieList_Sex.clear()

        getHomeMovieListUseCase.getHomeMovieList(Constants.USER_TOKEN, 5, "sex").subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","HomeViewModel/ 성별 top5 리스트 조회 성공")
                        if(it.body() != null){

                            it.body()!!.movies.forEach{
                                Log.e("AppTest", "성별/ 영화제목 : ${it.title},\n" +
                                        "포스터 경로 : ${it.poster_path}")
                                HomeMovieList_Sex.add(it) //
                            }

                            IsGetMovieListSex.value = true //
                        }
                        else{
                            Log.e("AppTest","HomeViewModel/ 성별 top5 리스트 empty")
                            IsGetMovieListSex.value = false //
                        }
                    }
                    else{
                        Log.e("AppTest","HomeViewModel/ 성별 top5 리스트 조회 실패, code : ${it.code()}")
                        IsGetMovieListSex.value = false //
                    }

                    progressbarVisibility_sex.value = false //
                },
                {
                    throwable ->
                    Log.e("AppTest","HomeViewModel/ 성별 top5 리스트 조회 오류")
                    progressbarVisibility_sex.value = false //
                    IsGetMovieListSex.value = false //
                }
        )
    }


    // 홈 영화 조회 - 지역
    @SuppressLint("CheckResult")
    fun getHomeMovieListByAddress(){
        //
        progressbarVisibility_address.value = true
        //리스트 비우고 시작!
        HomeMovieList_Address.clear()

        getHomeMovieListUseCase.getHomeMovieList(Constants.USER_TOKEN, 5, "address").subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","HomeViewModel/ 지역 top5 리스트 조회 성공")
                        if(it.body() != null){

                            it.body()!!.movies.forEach{
                                Log.e("AppTest", "지역/ 영화제목 : ${it.title},\n" +
                                        "포스터 경로 : ${it.poster_path}")
                                HomeMovieList_Address.add(it) //
                            }

                            IsGetMovieListAddress.value = true //
                        }
                        else{
                            Log.e("AppTest","HomeViewModel/ 지역 top5 리스트 empty")
                            IsGetMovieListAddress.value = false //
                        }
                    }
                    else{
                        Log.e("AppTest","HomeViewModel/ 지역 top5 리스트 조회 실패, code : ${it.code()}")
                        IsGetMovieListAddress.value = false //
                    }

                    progressbarVisibility_address.value = false //
                },
                {
                    throwable ->
                    Log.e("AppTest","HomeViewModel/ 지역 top5 리스트 조회 오류")
                    progressbarVisibility_address.value = false //
                    IsGetMovieListAddress.value = false //
                }
        )
    }

}