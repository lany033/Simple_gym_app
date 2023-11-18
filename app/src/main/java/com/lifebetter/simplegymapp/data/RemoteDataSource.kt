package com.lifebetter.simplegymapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

fun genericSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
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

fun getExercisesApi(
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

fun genericHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
}


/*
class SimpleInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("test","test")
            .build()
        return chain.proceed(request)
    }
}

private val client = OkHttpClient.Builder().apply {
    addInterceptor(SimpleInterceptor())
}.build()

 */