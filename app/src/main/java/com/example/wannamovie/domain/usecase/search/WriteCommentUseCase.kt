package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.domain.repository.SearchRepository

class WriteCommentUseCase (
        private val repository: SearchRepository
) {

    fun writeComment(userToken: String, requestDto: WriteCommentResquestDto) = repository.writeComment(userToken, requestDto)

}