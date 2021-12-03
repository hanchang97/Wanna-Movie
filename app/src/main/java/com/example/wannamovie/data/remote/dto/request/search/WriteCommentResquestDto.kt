package com.example.wannamovie.data.remote.dto.request.search

import com.google.gson.annotations.SerializedName

data class WriteCommentResquestDto(
        @SerializedName("movie_id")
        val movie_id : Int,
        @SerializedName("content")
        val content : String
)