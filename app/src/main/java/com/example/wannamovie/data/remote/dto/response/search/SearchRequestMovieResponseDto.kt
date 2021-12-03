package com.example.wannamovie.data.remote.dto.response.search

import com.google.gson.annotations.SerializedName

data class SearchRequestMovieResponseDto(
        @SerializedName("movie_id")
        val movie_id : Int,
        @SerializedName("content")
        val content : String
)