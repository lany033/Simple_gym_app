package com.lifebetter.simplegymapp.data.datasource

import arrow.core.Either
import com.lifebetter.simplegymapp.data.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.domain.Error

interface ExercisesRemoteDataSource {
    suspend fun getExercises(): Either<Error, ExerciseResult>
}