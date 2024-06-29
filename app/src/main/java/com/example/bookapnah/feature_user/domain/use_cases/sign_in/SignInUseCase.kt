package com.example.bookapnah.feature_user.domain.use_cases.sign_in

import android.app.Application
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_user.data.remote.dto.toUserDetails
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.domain.model.SignInData
import com.example.bookapnah.feature_user.domain.model.UserDetailsData
import com.example.bookapnah.feature_user.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor (
    private  val repository : UserRepository,
    private val application: Application
){
    operator  fun invoke(signInData: SignInData): Flow<Resource<UserDetailsData>> = flow {
        try{
            val preferenceUtil =  PreferenceUtil(application)
            var res = repository.signIn(signInData)
            val userDetails = res.user.toUserDetails()
            preferenceUtil.token = res.token
            preferenceUtil.login = true
            emit(Resource.Success<UserDetailsData>(userDetails))

        }catch(e: HttpException) {
            emit(Resource.Error<UserDetailsData>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<UserDetailsData>("Couldn't reach server. Check your internet connection."))
        }

    }
}