package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.data.ExerciseResult
import com.lifebetter.simplegymapp.data.RemoteDataSource

class ExercisesRepository {
    suspend fun getExercises(): ExerciseResult {
        return RemoteDataSource.service.getExercise()
    }
}