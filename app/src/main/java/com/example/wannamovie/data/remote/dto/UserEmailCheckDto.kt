package com.example.wannamovie.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserEmailCheckDto(
        @SerializedName("is_exist")
        val isExist : Boolean
)