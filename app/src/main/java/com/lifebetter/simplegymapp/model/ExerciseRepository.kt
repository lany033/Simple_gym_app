package com.lifebetter.simplegymapp.model

import android.util.Log
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.model.mappers.toTextEquipment
import com.lifebetter.simplegymapp.model.remotedata.ExerciseResult
import com.lifebetter.simplegymapp.model.remotedata.ExerciseService
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class ExercisesRepository @Inject constructor() {

    suspend fun getExercises(): ExerciseResult {
        return RemoteConnection.service().getExercise()
    }

    suspend fun getExercisesByLanguageEN(): List<Exercise>{
        return getExercises().results.map { it.toLocalModel() }.filter { it.language.short_name == "en" }
    }

    suspend fun findById(id: Int): Exercise{
        Log.d("findById", id.toString())
        return getExercises().results.map { it.toLocalModel() }.single { it.id == id }
    }

    suspend fun getExercisesFilterByBarbell(): List<Exercise> {
        return getExercisesByLanguageEN().filter { it.equipment.toTextEquipment().contains("Barbell") }
    }

    suspend fun getExercisesFilterBySZBar(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("SZ-Bar") }
    }

    suspend fun getExercisesFilterByDumbbell(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Dumbbell") }
    }

    suspend fun getExercisesFilterByGymMat(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Gym mat") }
    }

    suspend fun getExercisesFilterBySwissBall(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Swiss Ball") }
    }

    suspend fun getExercisesFilterByPullupBar(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Pull-up bar") }
    }
    suspend fun getExercisesFilterByNone(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("none (bodyweight exercise)") }
    }
    suspend fun getExercisesFilterByBench(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Bench") }
    }
    suspend fun getExercisesFilterByInclineBench(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Incline bench") }
    }
    suspend fun getExercisesFilterByKettlebell(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.equipment.toTextEquipment().contains("Kettlebell") }
    }

    suspend fun getExercisesFilterByBiceps(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Biceps brachii") }
    }
    suspend fun getExercisesFilterByDeltoids(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Anterior deltoid") }
    }
    suspend fun getExercisesFilterByChest(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Pectoralis major") }
    }
    suspend fun getExercisesFilterByTriceps(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Triceps brachii") }
    }
    suspend fun getExercisesFilterByAbd(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Rectus abdominis") }
    }
    suspend fun getExercisesFilterByGast(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Gastrocnemius") }
    }
    suspend fun getExercisesFilterByGluteus(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Gluteus maximus") }
    }
    suspend fun getExercisesFilterByQuad(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Quadriceps femoris") }
    }
    suspend fun getExercisesFilterByFemor(): List<Exercise> {
        return getExercises().results.map { it.toLocalModel() }.filter { it.muscles.toText().contains("Biceps femoris") }
    }


}

