package com.lifebetter.simplegymapp.model.remotedata

import com.lifebetter.simplegymapp.model.remotedata.muscle.MuscleResult
import retrofit2.http.GET

//limit=tamaño
//https://wger.de/api/v2/exercise/?limit=20&offset=0
//%3Flimit=20&offset=0
interface ExerciseService {
    @GET("exerciseinfo/")
    suspend fun getExercise(): ExerciseResult
    @GET("muscle/")
    suspend fun getMuscle(): MuscleResult

}