package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
import com.lifebetter.simplegymapp.model.remotedata.muscle.MuscleResult

class ExercisesRepository {
    suspend fun getExercises(): ExerciseResult {

        return RemoteConnection.service().getExercise()
    }

    suspend fun getMuscles(): MuscleResult {

        return RemoteConnection.service().getMuscle()
    }
}

