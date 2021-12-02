package com.example.wannamovie.data.remote.dto.response.user

import com.google.gson.annotations.SerializedName

data class UserInfoResponseDto(
        @SerializedName("email")
        val email : String,
        @SerializedName("name")
        val name : String,
        @SerializedName("address")
        val address : String,
        @SerializedName("age")
        val age : Int,
        @SerializedName("sex")
        val sex : String,
        @SerializedName("phone_no")
        val phone_no : String,
)