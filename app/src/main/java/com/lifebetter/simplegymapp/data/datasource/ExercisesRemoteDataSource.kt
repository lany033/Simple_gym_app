package com.lifebetter.simplegymapp.data.datasource

import arrow.core.Either
import com.lifebetter.simplegymapp.framework.server.ExerciseResult
import com.lifebetter.simplegymapp.domain.Error

interface ExercisesRemoteDataSource {
    suspend fun getExercises(): Either<com.lifebetter.simplegymapp.domain.Error, ExerciseResult>
}