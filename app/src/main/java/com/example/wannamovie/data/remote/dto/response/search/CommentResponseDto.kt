package com.example.wannamovie.data.remote.dto.response.search

import com.google.gson.annotations.SerializedName

data class CommentResponseDto(
        @SerializedName("comment_id")
        val comment_id : Int,
        @SerializedName("user_name")
        val user_name : String,
        @SerializedName("content")
        val content : String,
        @SerializedName("creation_date")
        val creation_date : String,
        @SerializedName("is_mine")
        val is_mine : Boolean
)