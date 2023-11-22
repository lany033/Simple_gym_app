package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.data.ExerciseResult
import com.lifebetter.simplegymapp.data.RemoteDataSource

class ExercisesRepository {
    suspend fun getExercises(limit: Int, offset: Int): ExerciseResult {
        return RemoteDataSource.service.getExercise(limit, offset)
    }
}