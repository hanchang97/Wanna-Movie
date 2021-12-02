package com.example.wannamovie.data.remote.dto.response.home

import com.google.gson.annotations.SerializedName

data class HomeMovieResponseDto(
        @SerializedName("movie_id")
        var movie_id : Int,
        @SerializedName("title")
        var title : String,
        @SerializedName("director")
        var director : String,
        @SerializedName("release_date")
        var release_date : String,
        @SerializedName("poster_path")
        var poster_path : String,
        @SerializedName("category_list")
        var category_list : ArrayList<String>,
        @SerializedName("request_count")
        var request_count : Int,
        @SerializedName("is_request")
        var is_request : Boolean
)