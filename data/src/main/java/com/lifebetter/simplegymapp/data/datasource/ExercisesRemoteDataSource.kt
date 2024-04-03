package com.lifebetter.simplegymapp.data.datasource

import arrow.core.Either
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.ExerciseResult

interface ExercisesRemoteDataSource {
    suspend fun getExercises(): Either<Error, ExerciseResult>
}