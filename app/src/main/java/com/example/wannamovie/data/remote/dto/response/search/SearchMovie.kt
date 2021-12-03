package com.example.wannamovie.data.remote.dto.response.search

import com.google.gson.annotations.SerializedName

data class SearchMovie(
        @SerializedName("movies")
        val movies : ArrayList<SearchMovieDto>,
        @SerializedName("total")
        val total : Int,
        @SerializedName("page_count")
        val page_count : Int,
        @SerializedName("page_size")
        val page_size : Int

)