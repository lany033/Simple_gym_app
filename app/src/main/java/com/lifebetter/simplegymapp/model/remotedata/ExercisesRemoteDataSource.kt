package com.lifebetter.simplegymapp.model.remotedata

import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection

class ExercisesRemoteDataSource {
    suspend fun getExercises(page: String): ExerciseResult {
        return RemoteConnection.provideBuilder().getExercise(page)
    }


}