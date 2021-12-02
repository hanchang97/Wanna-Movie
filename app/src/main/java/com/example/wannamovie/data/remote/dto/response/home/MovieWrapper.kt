package com.example.wannamovie.data.remote.dto.response.home

import com.google.gson.annotations.SerializedName

data class MovieWrapper(
        @SerializedName("movies")
        var movies : ArrayList<HomeMovieResponseDto>
)