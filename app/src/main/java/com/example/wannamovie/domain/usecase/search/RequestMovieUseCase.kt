package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.data.remote.dto.request.search.SearchRequestMovieResquestDto
import com.example.wannamovie.domain.repository.SearchRepository

class RequestMovieUseCase (
        private val repository: SearchRepository
) {

    fun requestMovie(userToken: String, requestMovieDto: SearchRequestMovieResquestDto)
    = repository.requestMovie(userToken, requestMovieDto)

}