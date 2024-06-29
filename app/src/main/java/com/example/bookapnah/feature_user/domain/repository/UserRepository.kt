package com.example.bookapnah.feature_user.domain.repository

import com.example.bookapnah.feature_user.data.remote.dto.UserResDto
import com.example.bookapnah.feature_user.data.remote.dto.user_details.UserDetailsDto
import com.example.bookapnah.feature_user.domain.model.SignInData
import com.example.bookapnah.feature_user.domain.model.SignUpData

interface UserRepository {
    suspend fun signUp(signUpData: SignUpData):UserResDto
    suspend fun signIn(signInData: SignInData):UserResDto

    suspend fun getUserDetails(token :String) :UserDetailsDto
}