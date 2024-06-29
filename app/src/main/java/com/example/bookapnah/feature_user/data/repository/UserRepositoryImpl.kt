package com.example.bookapnah.feature_user.data.repository


import com.example.bookapnah.feature_user.data.remote.UserApi
import com.example.bookapnah.feature_user.data.remote.dto.UserResDto
import com.example.bookapnah.feature_user.data.remote.dto.user_details.UserDetailsDto
import com.example.bookapnah.feature_user.domain.model.SignInData
import com.example.bookapnah.feature_user.domain.model.SignUpData
import com.example.bookapnah.feature_user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api:UserApi
) :UserRepository{
    override suspend fun signUp(signUpData: SignUpData): UserResDto {
        return api.signUp(signUpData)
    }

    override suspend fun signIn(signInData: SignInData): UserResDto {
       return  api.signIn(signInData)
    }

    override suspend fun getUserDetails(token: String): UserDetailsDto {
       return  api.getUserDetails(token)
    }


}