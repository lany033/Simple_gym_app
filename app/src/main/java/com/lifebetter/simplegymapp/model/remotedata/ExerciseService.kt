package com.lifebetter.simplegymapp.model.remotedata

import retrofit2.http.GET
import retrofit2.http.Query

//https://wger.de/api/v2/exercise/?limit=20&offset=0
interface ExerciseService {
    @GET("exerciseinfo/")
    suspend fun getExercise(@Query("limit") limit: Int, @Query("offset") offset: Int): ExerciseResult
}