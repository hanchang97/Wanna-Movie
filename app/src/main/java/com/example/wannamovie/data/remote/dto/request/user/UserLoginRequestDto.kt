package com.example.wannamovie.data.remote.dto.request.user

import com.google.gson.annotations.SerializedName

data class UserLoginRequestDto(
    @SerializedName("type")
    val type : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String,
)
