package com.example.wannamovie.data.remote.dto.response.search

import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.google.gson.annotations.SerializedName

data class MyRequestListWrappper (
    @SerializedName("movies")
    var movies : ArrayList<MyRequestListResponseDto>
    )