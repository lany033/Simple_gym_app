package com.lifebetter.simplegymapp.model.remotedata

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteConnection {


    fun provideHttpLoggingInterceptor(): OkHttpClient =

        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

    fun  provideBuilder(): ExerciseService = Retrofit.Builder()
        .baseUrl("https://wger.de/api/v2/")
        .client(provideHttpLoggingInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ExerciseService::class.java)


    fun service(): ExerciseService = provideBuilder()

}
