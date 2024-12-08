package com.example.bookapnah.feature_book.domain.user_cases.add_to_cart

import android.app.Application
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.data.remote.toCartRes
import com.example.bookapnah.feature_book.domain.model.CartItem
import com.example.bookapnah.feature_book.domain.model.CartResponse
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * USE CASE TO ADD BOOKS TO THE CART
 **/
class RemoveFromCartUseCase @Inject constructor(
    private  val repository: BooksRepository,
    private val application: Application
) {
    operator fun invoke(cartItem: CartItem): Flow<Resource<Unit>> = flow {
        try{
            val token = PreferenceUtil(application).token
            val responseCart = cartItem.name?.let { repository.removeFromCart(it,token) }
            emit(Resource.Success<Unit>(Unit))

        }catch(e: HttpException) {
            emit(Resource.Error<Unit>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<Unit>("Couldn't reach server. Check your internet connection."))
        }
    }
}