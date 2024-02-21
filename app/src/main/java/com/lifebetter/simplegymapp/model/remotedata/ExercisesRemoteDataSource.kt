package com.lifebetter.simplegymapp.model.remotedata

import android.util.Log
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.model.mappers.toTextEquipment
import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
import javax.inject.Inject

// TODO: interface exercises filters 
class ExercisesRemoteDataSource @Inject constructor(){
    suspend fun getExercises(): ExerciseResult {
        return RemoteConnection.service().getExercise()
    }

    suspend fun getExercisesByLanguageEN(): List<Exercise>{
        return getExercises().results.map { it.toLocalModel() }.filter { it.language.short_name == "en" }
    }




}