package com.example.wannamovie.presentation.main.tab.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.domain.entity.MovieSearched
import com.example.wannamovie.domain.usecase.search.SearchMovieUseCase

class SearchViewModel(
        private val searchMovieUseCase: SearchMovieUseCase
): ViewModel() {

    var SearchMovieList = ArrayList<MovieSearched>()

    var IsSearchMovieSuccess = MutableLiveData<Boolean>()
    var progressbarVisibility = MutableLiveData<Boolean>()

    var Current_Page = 0

    var IsLastItemIncluded = false  // movie_id 50이 포함되면 마지막까지 모두 탐색한 것임!!

    var firstItemMovieId = -1  // 조회 시 맨 처음 나오는 영화 id 값

    @SuppressLint("CheckResult")
    fun searchMovie(keyword: String, keyword_type: String){

        progressbarVisibility.value = true
        SearchMovieList.clear() // 리스트 비우고 시작!!

        var pageCount = 1

        Log.e("AppTest", "keyword length : ${keyword.length}")

        var keyMap = mutableMapOf<String, String>("keyword_type" to keyword_type)

        // 검색어 텍스트 존재하는 경우
        if(keyword.length > 0)
            keyMap.put("keyword", keyword)

        var pageMap =  mapOf<String, Int>("page_size" to 50, "page_count" to pageCount)   //  현재 최대 데이터 50개라 페이징 기능 사용x -> 전체 한번에 받는 중

        searchMovieUseCase.searchMovie(keyMap, pageMap).subscribe(
                {
                    if(it.code() == 200){
                        Log.e("AppTest","SearchViewModel/ 영화 검색 성공")


                            it.body()!!.movies.forEach {
                                Log.e("AppTest", "영화 제목 : ${it.title}")

                                var searchMovie = MovieSearched(it.movie_id, it.poster_path, it.title, 1)
                                SearchMovieList.add(searchMovie)

                            }

                            // 더보기 아이템뷰 위함 -> 마지막 아이템 아직 없을때..?
                               /* var searchMore = MovieSearched(0, "", "", 2)
                                SearchMovieList.add(searchMore)*/

                        IsSearchMovieSuccess.value = true
                    }
                    else{
                        Log.e("AppTest","SearchViewModel/ 영화 검색 실패")
                        IsSearchMovieSuccess.value = false
                    }

                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","SearchViewModel/ 영화 검색 오류")
                    IsSearchMovieSuccess.value = false
                    progressbarVisibility.value = false
                }
        )
    }

}