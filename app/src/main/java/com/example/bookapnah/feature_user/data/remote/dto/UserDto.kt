package com.example.bookapnah.feature_user.data.remote.dto

import com.example.bookapnah.feature_user.domain.model.UserDetailsData


data class UserDto(
    val __v: Int,
    val _id: String,
    val cart: List<Any>,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val role: String,
    val username: String
)


fun UserDto.toUserDetails(): UserDetailsData {
    return UserDetailsData(
        _id = _id,
        cart = cart,
        createdAt = createdAt,
        email = email,
        name = name,
        username = username
    )
}
