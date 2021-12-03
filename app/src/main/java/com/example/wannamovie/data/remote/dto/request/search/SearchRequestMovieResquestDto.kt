package com.example.wannamovie.data.remote.dto.request.search

import com.google.gson.annotations.SerializedName

data class SearchRequestMovieResquestDto(
        @SerializedName("movie_id")
        val movie_id : Int,
        @SerializedName("content")
        val content : String
)