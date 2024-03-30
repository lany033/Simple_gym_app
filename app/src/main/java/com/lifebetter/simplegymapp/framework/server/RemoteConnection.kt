package com.lifebetter.simplegymapp.framework.server

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
object RemoteConnection {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): OkHttpClient =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

    @Singleton
    @Provides
    fun  provideBuilder(): ExerciseService = Retrofit.Builder()
        .baseUrl("https://wger.de/api/v2/")
        .client(provideHttpLoggingInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ExerciseService::class.java)

    @Singleton
    @Provides
    fun service(): ExerciseService = provideBuilder()

}
