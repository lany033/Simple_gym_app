package com.lifebetter.simplegymapp.model.datasource

import com.lifebetter.simplegymapp.model.data.ExerciseResult
import com.lifebetter.simplegymapp.model.data.RemoteConnection

class ExerciseRemoteDataSource {
    suspend fun getExercises(page: Int, pageSize:Int): Result<ExerciseResult> {
        return Result.success(RemoteConnection.service.getExercise(page,pageSize))
    }
}