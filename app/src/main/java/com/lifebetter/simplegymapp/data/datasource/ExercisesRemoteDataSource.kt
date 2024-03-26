package com.lifebetter.simplegymapp.data.datasource

import arrow.core.Either
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
import javax.inject.Inject

// TODO: interface exercises filters 
class ExercisesRemoteDataSource @Inject constructor() {

    /*
    suspend fun getExercises(): Either<Error, ExerciseResult>

     */


    suspend fun getExercises(): ExerciseResult {
        return RemoteConnection.service().getExercise()
    }




}