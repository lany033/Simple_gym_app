package com.lifebetter.simplegymapp.model.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseLocalDataSource @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutSessionDao: WorkoutSessionDao
) {
    val workouts: Flow<List<Workout>> = workoutDao.getAll()

    suspend fun isEmpty(): Boolean = workoutDao.workoutCount() == 0

    fun findById(id: Int): Flow<Workout> = workoutDao.findById(id)

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)

    suspend fun saveWorkout(list: List<Workout>) {
        workoutDao.insertWorkout(list)
    }

    suspend fun saveWorkoutSession(workoutSession: WorkoutSession){
        workoutSessionDao.insertWorkoutSession(workoutSession)
    }

    fun getWorkoutSession(): Flow<List<WorkoutSession>>{
        return workoutSessionDao.getAll()
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession) = workoutSessionDao.deleteWorkoutSession(workoutSession)


}