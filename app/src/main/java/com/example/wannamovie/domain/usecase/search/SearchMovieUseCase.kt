package com.example.wannamovie.domain.usecase.search

import com.example.wannamovie.domain.repository.SearchRepository

class SearchMovieUseCase (
        private val repository: SearchRepository
) {

    fun searchMovie(keyMap: Map<String, String>, pageMap: Map<String, Int>) = repository.searchMovie(keyMap, pageMap)

}