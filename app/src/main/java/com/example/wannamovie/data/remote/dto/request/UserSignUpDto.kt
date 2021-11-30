package com.example.wannamovie.data.remote.dto.request

import com.google.gson.annotations.SerializedName

data class UserSignUpDto(
        @SerializedName("email")
        val email : String,
        @SerializedName("name")
        val name : String,
        @SerializedName("age")
        val age : Int,
        @SerializedName("profession")
        val profession : String,
        @SerializedName("address")
        val address : String,
        @SerializedName("sex")
        val gender : String,
        @SerializedName("password")
        val password : String,
        @SerializedName("password_confirm")
        val password_confirm : String
)