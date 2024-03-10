package com.lifebetter.simplegymapp.model.database

import com.lifebetter.simplegymapp.domain.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseLocalDataSource @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val setDao: SetDao
) {
    val workouts: Flow<List<Workout>> = workoutDao.getAll()

    suspend fun isEmpty(): Boolean = workoutDao.workoutCount() == 0

    suspend fun findById(id: Int): Flow<List<Exercise>> =
        workoutDao.findById(id).map { it.exerciseList }

    suspend fun deleteWorkout(workout: Workout) = workoutDao.deleteWorkout(workout)

    suspend fun saveWorkout(list: List<Workout>) {
        workoutDao.insertWorkout(list)
    }

    suspend fun saveSetWorkout(list: List<SetWorkout>){
        setDao.insertWorkout(list)
    }


}