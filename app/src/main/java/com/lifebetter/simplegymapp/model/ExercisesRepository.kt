package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteDataSource

class ExercisesRepository {
    suspend fun getExercises(limit: Int, offset: Int): ExerciseResult {
        return RemoteDataSource.service.getExercise(limit, offset)
    }
}