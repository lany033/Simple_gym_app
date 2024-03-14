package com.lifebetter.simplegymapp.model

import android.util.Log
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.database.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.model.mappers.toTextEquipment
import com.lifebetter.simplegymapp.model.remotedata.ExercisesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ExercisesRepository @Inject constructor(
    private val exercisesRemoteDataSource: ExercisesRemoteDataSource,
    private val exerciseLocalDataSource: ExerciseLocalDataSource
) {
    val workouts = exerciseLocalDataSource.workouts

    suspend fun saveWorkoutSession(workoutSession: WorkoutSession){
        exerciseLocalDataSource.saveWorkoutSession(workoutSession)
    }

    suspend fun getAllWorkoutSessions(): Flow<List<WorkoutSession>>{
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<Workout>) {
        exerciseLocalDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: Workout){
        exerciseLocalDataSource.deleteWorkout(workout)
    }

    suspend fun findByWorkoutId(id: Int): Flow<Workout>{
        return exerciseLocalDataSource.findById(id)
    }

    suspend fun requestExercises(): List<Exercise> {
        return exercisesRemoteDataSource.getExercises().results.map { it.toLocalModel() }
    }

    suspend fun findByExerciseId(id: Int): Exercise {
        Log.d("findById", id.toString())
        return requestExercises().single { it.id == id }
    }

    suspend fun getExercisesFilterByBarbell(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Barbell") }
    }

    suspend fun getExercisesFilterBySZBar(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("SZ-Bar") }
    }

    suspend fun getExercisesFilterByDumbbell(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Dumbbell") }
    }

    suspend fun getExercisesFilterByGymMat(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Gym mat") }
    }

    suspend fun getExercisesFilterBySwissBall(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Swiss Ball") }
    }

    suspend fun getExercisesFilterByPullupBar(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Pull-up bar") }
    }

    suspend fun getExercisesFilterByNone(): List<Exercise> {
        return requestExercises().filter {
            it.equipment.toTextEquipment().contains("none (bodyweight exercise)")
        }
    }

    suspend fun getExercisesFilterByBench(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Bench") }
    }

    suspend fun getExercisesFilterByInclineBench(): List<Exercise> {
        return requestExercises().filter {
            it.equipment.toTextEquipment().contains("Incline bench")
        }
    }

    suspend fun getExercisesFilterByKettlebell(): List<Exercise> {
        return requestExercises().filter { it.equipment.toTextEquipment().contains("Kettlebell") }
    }

    suspend fun getExercisesFilterByBiceps(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Biceps brachii") }
    }

    suspend fun getExercisesFilterByDeltoids(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Anterior deltoid") }
    }

    suspend fun getExercisesFilterByChest(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Pectoralis major") }
    }

    suspend fun getExercisesFilterByTriceps(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Triceps brachii") }
    }

    suspend fun getExercisesFilterByAbd(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Rectus abdominis") }
    }

    suspend fun getExercisesFilterByGast(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Gastrocnemius") }
    }

    suspend fun getExercisesFilterByGluteus(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Gluteus maximus") }
    }

    suspend fun getExercisesFilterByQuad(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Quadriceps femoris") }
    }

    suspend fun getExercisesFilterByFemor(): List<Exercise> {
        return requestExercises().filter { it.muscles.toText().contains("Biceps femoris") }
    }


}

