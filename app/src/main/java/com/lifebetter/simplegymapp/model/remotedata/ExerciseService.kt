package com.lifebetter.simplegymapp.model.remotedata

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//limit=tamaño
//https://wger.de/api/v2/exercise/?limit=20&offset=0
//%3Flimit=20&offset=0
interface ExerciseService {
    @GET("exerciseinfo")
    suspend fun getExercise(
        @Query("page") page: String
    ): ExerciseResult

}