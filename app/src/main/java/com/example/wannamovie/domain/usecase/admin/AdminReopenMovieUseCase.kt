package com.example.wannamovie.domain.usecase.admin

import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.domain.repository.AdminRepository

class AdminReopenMovieUseCase (
        private val repository: AdminRepository
) {

    fun reopenMovie(userToken: String, movieIdDto: AdminReopenMovieRequestDto)
            = repository.reopenMovie(userToken, movieIdDto)

}