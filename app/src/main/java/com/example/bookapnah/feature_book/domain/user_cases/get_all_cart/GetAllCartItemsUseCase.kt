package com.example.bookapnah.feature_book.domain.user_cases.get_all_cart

import android.app.Application
import android.util.Log
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.data.remote.dto.cart.CartBooks
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCartItemsUseCase @Inject constructor(
    private  val repository: BooksRepository,
    private val application: Application
) {

    operator fun invoke(): Flow<Resource<CartBooks>> = flow {

        try{
            // getting the token from shared preferences
            val token = PreferenceUtil(application).token

            // requesting the server for all the cart items
            val books = repository.getAllCart(token)

            // emitting the response with success
            emit(Resource.Success<CartBooks>(books))

        }catch(e: HttpException) {
            // emitting the response with error
            emit(Resource.Error<CartBooks>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            // emitting the response with error
            emit(Resource.Error<CartBooks>("Couldn't reach server. Check your internet connection."))
        }
    }
}