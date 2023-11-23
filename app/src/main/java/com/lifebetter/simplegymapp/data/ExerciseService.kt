package com.lifebetter.simplegymapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseService {
    @GET("exerciseinfo/")
    suspend fun getExercise(@Query("limit") limit: Int, @Query("offset") offset: Int): ExerciseResult
}