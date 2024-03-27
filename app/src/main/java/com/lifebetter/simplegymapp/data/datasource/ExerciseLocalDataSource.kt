package com.lifebetter.simplegymapp.data.datasource

import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.tryCall
import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.model.database.ExerciseDao
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutDao
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.model.database.WorkoutSessionDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseLocalDataSource @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutSessionDao: WorkoutSessionDao,
    private val exerciseDao: ExerciseDao
) {
    val workouts: Flow<List<Workout>> = workoutDao.getAll()

    val exercises: Flow<List<Exercise>> = exerciseDao.getAll()

    suspend fun isExerciseListEmpty(): Boolean = exerciseDao.exerciseCount() == 0

    suspend fun saveExercise(list: List<Exercise>): Error? = tryCall {
        exerciseDao.insertExercise(list)
    }.fold(ifLeft = {it}, ifRight = {null})

    fun findExerciseById(id: Int): Flow<Exercise> = exerciseDao.findByIdExercise(id)


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