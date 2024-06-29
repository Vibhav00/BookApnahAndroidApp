package com.example.bookapnah.feature_user.data.remote

import com.example.bookapnah.feature_user.data.remote.dto.UserResDto
import com.example.bookapnah.feature_user.data.remote.dto.user_details.UserDetailsDto
import com.example.bookapnah.feature_user.domain.model.SignInData
import com.example.bookapnah.feature_user.domain.model.SignUpData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("/users/register")
    suspend fun signUp(@Body signUpData: SignUpData): UserResDto


    @POST("users/login")
    suspend fun signIn(@Body signInData: SignInData): UserResDto


    @GET("users/userdetails")
    suspend fun getUserDetails(
        @Query("token")
        token: String
    ): UserDetailsDto
}