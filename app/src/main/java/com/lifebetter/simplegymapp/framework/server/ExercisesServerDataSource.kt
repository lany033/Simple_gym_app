package com.lifebetter.simplegymapp.framework.server

import arrow.core.Either
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.framework.tryCall
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.ExerciseResult
import com.lifebetter.simplegymapp.framework.toDomain
import com.lifebetter.simplegymapp.framework.toExerciseResultDomain
import javax.inject.Inject

class ExercisesServerDataSource @Inject constructor() : ExercisesRemoteDataSource {

    override suspend fun getExercises(): Either<Error, List<Exercise>> =

        tryCall {
            RemoteConnection.service().getExercise().results.toDomainModel()
        }


}

private fun List<ExerciseDto>.toDomainModel(): List<Exercise> = map{ it.toDomain() }