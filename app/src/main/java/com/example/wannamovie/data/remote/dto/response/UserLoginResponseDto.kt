package com.example.wannamovie.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponseDto(
    @SerializedName("token")
    val token : String
)