package com.example.bookapnah.feature_user.data.remote.dto.user_details

import com.example.bookapnah.feature_user.domain.model.UserDetailsData

data class User(
    val __v: Int,
    val _id: String,
    val cart: List<Any>,
    val createdAt: String,
    val email: String,
    val name: String,
    val role: String,
    val username: String
)

fun User.toUserDetails(): UserDetailsData {
    return UserDetailsData(
        _id,
        cart,
        createdAt, email, name, username

    )
}