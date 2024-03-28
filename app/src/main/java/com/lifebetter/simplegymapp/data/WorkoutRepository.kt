package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val exerciseLocalDataSource: ExerciseLocalDataSource
) {
    val workouts = exerciseLocalDataSource.workouts

    suspend fun saveWorkoutSession(workoutSession: WorkoutSession){
        exerciseLocalDataSource.saveWorkoutSession(workoutSession)
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession){
        exerciseLocalDataSource.deleteWorkoutSession(workoutSession)
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSession>> {
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<Workout>) {
        exerciseLocalDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: Workout){
        exerciseLocalDataSource.deleteWorkout(workout)
    }

    fun findByWorkoutId(id: Int): Flow<Workout> {
        return exerciseLocalDataSource.findByWorkoutId(id)
    }
}