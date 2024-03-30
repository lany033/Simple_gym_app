package com.lifebetter.simplegymapp.model.datasource

class ExerciseRemoteDataSource {
    suspend fun getExercises(page: Int, pageSize:Int): Result<ExerciseResult> {
        return Result.success(RemoteConnection.service.getExercise(page,pageSize))
    }
}