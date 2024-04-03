package com.lifebetter.simplegymapp.framework.server

import arrow.core.Either
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.framework.tryCall
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.ExerciseResult
import com.lifebetter.simplegymapp.framework.toExerciseResultDomain
import javax.inject.Inject

class ExercisesServerDataSource @Inject constructor() : ExercisesRemoteDataSource {

    override suspend fun getExercises(): Either<Error, ExerciseResult> =
        tryCall {
            RemoteConnection.service().getExercise().toExerciseResultDomain()
        }


}