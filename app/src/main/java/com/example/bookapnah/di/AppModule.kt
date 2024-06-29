package com.example.bookapnah.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.bookapnah.core.domain.common.Constants
import com.example.bookapnah.feature_book.data.data_source.BookDatabase
import com.example.bookapnah.feature_book.data.remote.BooksApi
import com.example.bookapnah.feature_book.data.repository.BookRepositoryImpl
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import com.example.bookapnah.feature_user.data.remote.UserApi
import com.example.bookapnah.feature_user.data.repository.UserRepositoryImpl
import com.example.bookapnah.feature_user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    /**
     * API FOR BOOKS
     **/
    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit):BooksApi{
        return retrofit
            .create(BooksApi::class.java)
    }


    /**
     * PROVIDING THE RETROFIT INSTANCE TO CREATE DIFFERENT APIS
     **/
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        // logging interceptor for debugging
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Create an OkHttpClient and attach the logging interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        //  Creating the instance of the retrofit
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * CREATING USER API
     **/
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit):UserApi{
        return retrofit
            .create(UserApi::class.java)
    }



    @Provides
    @Singleton
    fun providesBookRepository(api:BooksApi,db:BookDatabase):BooksRepository{
        return BookRepositoryImpl(api,db.booksDao)
    }


    @Provides
    @Singleton
    fun providesUserRepository(api:UserApi):UserRepository{
        return UserRepositoryImpl(api)
    }


    /** Creating Room Database Instance **/
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): BookDatabase{
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            BookDatabase.DATABASE_NAME
        ).build()
    }

}