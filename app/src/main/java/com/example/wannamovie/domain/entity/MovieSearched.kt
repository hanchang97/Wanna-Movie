package com.example.wannamovie.domain.entity

data class MovieSearched(
        var movie_id : Int,
        var poster_path: String,
        var movie_title: String,
        var itemview_type : Int
)