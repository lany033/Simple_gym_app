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

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession){
        exerciseLocalDataSource.deleteWorkoutSession(workoutSession)
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSession>>{
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<Workout>) {
        exerciseLocalDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: Workout){
        exerciseLocalDataSource.deleteWorkout(workout)
    }

    fun findByWorkoutId(id: Int): Flow<Workout>{
        return exerciseLocalDataSource.findById(id)
    }

    suspend fun requestExercises(): List<Exercise> {
        return exercisesRemoteDataSource.getExercises().results.map { it.toLocalModel() }
    }

    suspend fun findByExerciseId(id: Int): Exercise {
        Log.d("findById", id.toString())
        return requestExercises().single { it.id == id }
    }

}

