package com.lifebetter.simplegymapp.data

import android.util.Log

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.tryCall
import com.lifebetter.simplegymapp.model.database.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

//TODO: Repository de workouts

class ExercisesRepository @Inject constructor(
    private val exercisesRemoteDataSource: ExercisesRemoteDataSource,
    private val exerciseLocalDataSource: ExerciseLocalDataSource
) {
    val workouts = exerciseLocalDataSource.workouts

    val exercises = exerciseLocalDataSource.exercises

    suspend fun saveWorkoutSession(workoutSession: WorkoutSession) {
        exerciseLocalDataSource.saveWorkoutSession(workoutSession)
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession) {
        exerciseLocalDataSource.deleteWorkoutSession(workoutSession)
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSession>> {
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<Workout>) {
        exerciseLocalDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: Workout) {
        exerciseLocalDataSource.deleteWorkout(workout)
    }

    fun findByWorkoutId(id: Int): Flow<Workout> {
        return exerciseLocalDataSource.findById(id)
    }

    suspend fun requestExercises(): Error? {
        if (exerciseLocalDataSource.isExerciseListEmpty()) {
            val exercisesList =
                exercisesRemoteDataSource.getExercises()
            exercisesList.fold(ifLeft = { return it }) {
                exerciseLocalDataSource.saveExercise(
                    it.results.map { it.toLocalModel() }
                )
            }
        }
        return null
    }


    fun findByExerciseId(id: Int): Flow<Exercise> {

        return exerciseLocalDataSource.findExerciseById(id)

    }


}

