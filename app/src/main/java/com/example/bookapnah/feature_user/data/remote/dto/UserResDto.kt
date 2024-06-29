package com.example.bookapnah.feature_user.data.remote.dto

data class UserResDto(
    val success: Boolean,
    val token: String,
    val user: UserDto
)