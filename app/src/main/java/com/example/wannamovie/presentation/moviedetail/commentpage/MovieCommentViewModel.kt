package com.example.wannamovie.presentation.moviedetail.commentpage

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wannamovie.common.Constants
import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.domain.usecase.search.SearchMovieDetailUseCase
import com.example.wannamovie.domain.usecase.search.WriteCommentUseCase

class MovieCommentViewModel(
        private val searchMovieDetailUseCase: SearchMovieDetailUseCase,
        private val writeCommentUseCase: WriteCommentUseCase
): ViewModel() {

    var IsGetMovieDetailSuccess = MutableLiveData<Boolean>()
    var progressbarVisibility = MutableLiveData<Boolean>()

    var CommentList = ArrayList<CommentResponseDto>()

    var IsWriteCommentSuccess = MutableLiveData<Boolean>()
    var progressbarVisibility_writeComment = MutableLiveData<Boolean>()


    // 영화 상세 조회 후 댓글 리스트 가져오기 위함
    @SuppressLint("CheckResult")
    fun getMovieDetailInfo(movieId: Int){
        progressbarVisibility.value = true
        CommentList.clear()

        searchMovieDetailUseCase.getMovieDetailInfo(Constants.USER_TOKEN, movieId).subscribe(
                {
                    if(it.code() == 200){

                        Log.e("AppTest", "MovieCommentViewModel/ 영화 상세 정보 조회 성공\n")

                        it.body()!!.comments.forEach{
                            Log.e("AppTest", "댓글 내용 : ${it.content}")

                            CommentList.add(it)
                        }

                        IsGetMovieDetailSuccess.value = true
                    }
                    else{
                        Log.e("AppTest","MovieCommentViewModel/ 영화 상세 정보 조회 실패")
                        IsGetMovieDetailSuccess.value = false
                    }

                    progressbarVisibility.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","MovieCommentViewModel/ 영화 상세 정보 조회 오류")
                    progressbarVisibility.value = false
                    IsGetMovieDetailSuccess.value = false
                }
        )
    }
    /////////////////////////////


    @SuppressLint("CheckResult")
    fun writeComment(movie_id: Int, content: String){
        var requestDto = WriteCommentResquestDto(movie_id, content) // request body

        progressbarVisibility_writeComment.value = true

        writeCommentUseCase.writeComment(Constants.USER_TOKEN, requestDto).subscribe(
                {
                    if(it.code() == 201){
                        Log.e("AppTest","MovieCommentViewModel/ 댓글 등록 성공")
                        IsWriteCommentSuccess.value = true

                    }
                    else{
                        Log.e("AppTest","MovieCommentViewModel/ 댓글 등록 실패")
                        IsWriteCommentSuccess.value = false
                    }

                    progressbarVisibility_writeComment.value = false
                },
                {
                    throwable ->
                    Log.e("AppTest","MovieCommentViewModel/ 댓글 등록 오류")
                    progressbarVisibility_writeComment.value = false
                    IsWriteCommentSuccess.value = false
                }
        )


    }

}