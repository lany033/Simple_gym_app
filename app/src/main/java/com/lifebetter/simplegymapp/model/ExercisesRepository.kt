package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.data.ExerciseResult
import com.lifebetter.simplegymapp.data.RemoteDataSource

class ExercisesRepository {
    suspend fun getExercises(page: Int, pageSize: Int): Result<ExerciseResult> {
        return Result.success(RemoteDataSource.service.getExercise(pageSize,page))
    }
}