package com.example.wannamovie.data.remote.dto.request.admin

import com.google.gson.annotations.SerializedName

data class AdminReopenMovieRequestDto(
        @SerializedName("movie_id")
        val movie_id : Int
)