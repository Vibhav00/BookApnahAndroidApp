package com.example.bookapnah.feature_user.domain.use_cases.sign_up

import android.app.Application
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_user.data.remote.dto.toUserDetails
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.domain.model.SignUpData
import com.example.bookapnah.feature_user.domain.model.UserDetailsData
import com.example.bookapnah.feature_user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private  val repository: UserRepository,
    private val application: Application
){
    operator  fun invoke(signUpData: SignUpData ): Flow<Resource<UserDetailsData>> = flow {
        try{
            // creating instance of shared preferences
            val preferenceUtil =  PreferenceUtil(application)

            // calling the api
            val response  = repository.signUp(signUpData)

            // converting the response into user Details
            val userDetails = response.user.toUserDetails()

            // collecting the token from the response
            preferenceUtil.token = response.token

            // updating login status
            preferenceUtil.login = true

            // updating the user details in shared preferences
            PreferenceUtil(application).put(
                    userDetails,
                PreferenceUtil.USER_DETAILS
            )

            // emitting the response with success
            emit(Resource.Success<UserDetailsData>(userDetails))

        }catch(e: HttpException) {
            // emitting the response with error
            emit(Resource.Error<UserDetailsData>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            // emitting the response with error
            emit(Resource.Error<UserDetailsData>("Couldn't reach server. Check your internet connection."))
        }

    }
}