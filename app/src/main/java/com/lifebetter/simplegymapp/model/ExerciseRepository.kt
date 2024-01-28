package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection

class ExercisesRepository {
    suspend fun getExercises(): ExerciseResult {

        return RemoteConnection.service().getExercise()
    }

}

