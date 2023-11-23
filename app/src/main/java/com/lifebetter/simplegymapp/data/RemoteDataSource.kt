package com.lifebetter.simplegymapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RemoteDataSource {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    private val builder = Retrofit.Builder()
        .baseUrl("https://wger.de/api/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ExerciseService = builder.create()


    /*

    private fun genericHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private fun genericSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            //.addInterceptor(ConnectVerifierInterceptor())//captura los timeouts, se deshabilita los reintentos
            .build()
    }

    suspend fun getExercises(): List<Exercise> {
        val okHttpClient = genericSafeOkHttpClient(genericHttpLoggingInterceptor())
        val exerciseService = getExercisesApi(
            okHttpClient,
            "application/json".toMediaType(),
            json = Json { ignoreUnknownKeys = true }
        )
        val exercise = exerciseService.getExercise().results.map {
            it.toExercise()
        }
        return  exercise
    }

    private fun getExercisesApi(
        genericOkHttpClient: OkHttpClient,
        contentType: MediaType,
        json: Json,
    ): ExerciseService {
        return Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")
            .client(genericOkHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ExerciseService::class.java)
    }

     */

}
