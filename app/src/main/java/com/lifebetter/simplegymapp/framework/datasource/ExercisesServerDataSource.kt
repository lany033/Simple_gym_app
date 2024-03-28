package com.lifebetter.simplegymapp.framework.datasource

import arrow.core.Either
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.tryCall
import com.lifebetter.simplegymapp.data.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.data.remotedata.RemoteConnection
import javax.inject.Inject



class ExercisesServerDataSource @Inject constructor() : ExercisesRemoteDataSource {

    override suspend fun getExercises(): Either<Error, ExerciseResult> = tryCall {
        RemoteConnection.service().getExercise()
    }


}