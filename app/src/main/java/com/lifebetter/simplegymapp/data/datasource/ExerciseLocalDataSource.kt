package com.lifebetter.simplegymapp.data.datasource

import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.Workout
import com.lifebetter.simplegymapp.domain.WorkoutSession
import kotlinx.coroutines.flow.Flow

interface ExerciseLocalDataSource {
    val workouts: Flow<List<Workout>>
    val exercises: Flow<List<Exercise>>

    suspend fun isExerciseListEmpty(): Boolean
    suspend fun saveExercise(list: List<Exercise>): Error?
    fun findExerciseById(id: Int): Flow<Exercise>
    fun findByWorkoutId(id: Int): Flow<Workout>

    suspend fun deleteWorkout(workout: Workout)
    suspend fun saveWorkout(list: List<Workout>)

    suspend fun saveWorkoutSession(workoutSession: WorkoutSession)
    fun getWorkoutSession(): Flow<List<WorkoutSession>>

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession)
}