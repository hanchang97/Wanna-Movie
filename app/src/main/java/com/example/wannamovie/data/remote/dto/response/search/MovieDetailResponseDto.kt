package com.example.wannamovie.data.remote.dto.response.search

import com.google.gson.annotations.SerializedName

data class MovieDetailResponseDto(
        @SerializedName("title")
        val title : String,
        @SerializedName("director")
        val director : String,
        @SerializedName("release_date")
        val release_date : String,
        @SerializedName("poster_path")
        val poster_path : String,
        @SerializedName("category_list")
        val category_list : ArrayList<String>,
        @SerializedName("comments")
        val comments : ArrayList<CommentResponseDto>,
        @SerializedName("request_count")
        val request_count : Int,
        @SerializedName("is_request")
        val is_request : Boolean
)