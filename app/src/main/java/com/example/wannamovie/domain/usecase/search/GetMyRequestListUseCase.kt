package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.domain.repository.SearchRepository

class GetMyRequestListUseCase (
        private val repository: SearchRepository
) {

    fun getMyRequestList(userToken: String)
            = repository.getMyRequestList(userToken)

}