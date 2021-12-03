package com.example.wannamovie.data.remote.dto.response.search

import com.google.gson.annotations.SerializedName

data class SearchMovieDto(
        @SerializedName("no")
        val no : Int,
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
        var category_list : ArrayList<String>
)