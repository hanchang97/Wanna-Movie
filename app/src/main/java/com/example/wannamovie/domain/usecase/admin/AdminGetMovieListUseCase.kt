package com.example.wannamovie.domain.usecase.admin

import com.example.wannamovie.domain.repository.AdminRepository
import com.example.wannamovie.domain.repository.HomeRepository

class AdminGetMovieListUseCase(
        private val repository: AdminRepository
) {

    fun getAdminMovieList(userToken: String, top_count: Int, keyword: String, keyword_value: String)
            = repository.getAdminMovieList(userToken, top_count, keyword, keyword_value)

}