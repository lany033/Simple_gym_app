package com.lifebetter.simplegymapp.model.remotedata

import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection

class ExercisesRemoteDataSource {
    suspend fun getExercises(limit: Int, offset: Int): ExerciseResult {
        return RemoteConnection.service.getExercise(limit, offset)
    }


}