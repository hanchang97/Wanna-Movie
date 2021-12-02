package com.example.wannamovie.domain.usecase.home

import com.example.wannamovie.domain.repository.HomeRepository

class HomeGetMovieListUseCase(
        private val repository: HomeRepository
) {

    fun getHomeMovieList(userToken: String, top_count: Int, keyword: String) = repository.getHomeMovieList(userToken, top_count, keyword)


}