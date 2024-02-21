package com.lifebetter.simplegymapp.model.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseLocalDataSource @Inject constructor(private val workoutDao: WorkoutDao) {
    val workouts: Flow<List<Workout>> = workoutDao.getAll()

   suspend fun isEmpty(): Boolean = workoutDao.workoutCount() == 0

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)

    suspend fun save(list: List<Workout>) {
        workoutDao.insertWorkout(list)
    }


}