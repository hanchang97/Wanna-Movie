package com.example.wannamovie.data.remote.dto.response.admin

import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.google.gson.annotations.SerializedName

data class AdminMovieWrapper(
        @SerializedName("movies")
        var movies : ArrayList<AdminMovieListResponseDto>
)