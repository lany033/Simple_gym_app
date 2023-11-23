package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.data.ExerciseResult
import com.lifebetter.simplegymapp.data.RemoteDataSource

class ExercisesRepository {
<<<<<<< HEAD
    suspend fun getExercises(page: Int, pageSize: Int): Result<ExerciseResult> {
        return Result.success(RemoteDataSource.service.getExercise(pageSize,page))
=======
    suspend fun getExercises(limit: Int, offset: Int): ExerciseResult {
        return RemoteDataSource.service.getExercise(limit, offset)
>>>>>>> main
    }
}