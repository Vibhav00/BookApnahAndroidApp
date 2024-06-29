package com.example.bookapnah.feature_user.domain.use_cases.user_details

import android.app.Application
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_user.data.remote.dto.user_details.toUserDetails
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.domain.model.UserDetailsData
import com.example.bookapnah.feature_user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(

    private val repository: UserRepository,
    private val application: Application
) {
    operator fun  invoke(): Flow<Resource<UserDetailsData>> = flow{
        try {
            // obtaining the token from the shared preferences
            val token = PreferenceUtil(application).token

            // calling the api for the user details
            val ans = repository.getUserDetails(token).user.toUserDetails()

            // emitting the response with the success
            emit(Resource.Success<UserDetailsData>(ans))

        }catch (e :Exception){
            emit(Resource.Error<UserDetailsData>("got some error : $e"))
        }

    }
}