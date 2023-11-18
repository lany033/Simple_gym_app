package com.lifebetter.simplegymapp.data

import retrofit2.http.GET

interface ExerciseService {
    @GET("exercise/")
    suspend fun getExercise(): ExerciseResult
}