package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.data.remote.dto.request.search.WriteCommentResquestDto
import com.example.wannamovie.domain.repository.SearchRepository

class IncreaseMovieVisitUseCase (
        private val repository: SearchRepository
) {

    fun increaseMovieVisit(movie_id: Int) = repository.increaseMovieVisit(movie_id)

}