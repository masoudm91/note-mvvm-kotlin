package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.BuildConfig
import com.example.noteapp.room.AppDatabase
import com.example.noteapp.utils.AppConstants.BASE_URL
import com.example.noteapp.utils.AppConstants.DB_NAME
import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * dependency injection with hilt
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application,AppDatabase::class.java, DB_NAME)
            .build()



    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}