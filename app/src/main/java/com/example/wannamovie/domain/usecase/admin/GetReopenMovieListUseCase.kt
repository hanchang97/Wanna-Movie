package com.example.wannamovie.domain.usecase.admin

import com.example.wannamovie.data.remote.dto.request.admin.AdminReopenMovieRequestDto
import com.example.wannamovie.domain.repository.AdminRepository

class GetReopenMovieListUseCase (
        private val repository: AdminRepository
) {

    fun getReopenMovieList()
            = repository.getReopenMovieList()

}