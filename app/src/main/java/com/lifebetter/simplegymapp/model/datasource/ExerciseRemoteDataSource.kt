package com.lifebetter.simplegymapp.model.datasource

import com.lifebetter.simplegymapp.model.data.ExerciseResult
import com.lifebetter.simplegymapp.model.data.RemoteConnection

class ExerciseRemoteDataSource(private val page: Int, private val pageSize: Int) {
    suspend fun getExercises(): Result<ExerciseResult> {
        return Result.success(RemoteConnection.service.getExercise(pageSize,page))
    }
}