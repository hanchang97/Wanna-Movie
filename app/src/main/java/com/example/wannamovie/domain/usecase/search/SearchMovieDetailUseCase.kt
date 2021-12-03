package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.domain.repository.HomeRepository
import com.example.wannamovie.domain.repository.SearchRepository

class SearchMovieDetailUseCase (
        private val repository: SearchRepository
) {

    fun getMovieDetailInfo(userToken: String, movieId: Int) = repository.getMovieDetailInfo(userToken, movieId)

}