package com.example.bookapnah.feature_user.domain.model

data class UserDetailsData(
    val _id: String,
    val cart: List<Any>,
    val createdAt: String,
    val email: String,
    val name: String,
    val username: String
)
