package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.toDomain
import com.lifebetter.simplegymapp.domain.Workout as WorkoutDomain
import com.lifebetter.simplegymapp.domain.WorkoutSession as WorkoutSessionDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//TODO: Repository de workouts

class ExercisesRepository @Inject constructor(
    private val exercisesRemoteDataSource: ExercisesRemoteDataSource,
    private val exerciseLocalDataSource: ExerciseLocalDataSource
) {
    val workouts = exerciseLocalDataSource.workouts

    val exercises = exerciseLocalDataSource.exercises

    suspend fun saveWorkoutSession(workoutSession: WorkoutSessionDomain) {
        exerciseLocalDataSource.saveWorkoutSession(workoutSession)
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSessionDomain) {
        exerciseLocalDataSource.deleteWorkoutSession(workoutSession)
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSessionDomain>> {
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<WorkoutDomain>) {
        exerciseLocalDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: WorkoutDomain) {
        exerciseLocalDataSource.deleteWorkout(workout)
    }

    fun findByWorkoutId(id: Int): Flow<WorkoutDomain> {
        return exerciseLocalDataSource.findByWorkoutId(id)
    }

    suspend fun requestExercises(): Error? {
        if (exerciseLocalDataSource.isExerciseListEmpty()) {
            val exercisesList =
                exercisesRemoteDataSource.getExercises()
            exercisesList.fold(ifLeft = { return it }) {
                exerciseLocalDataSource.saveExercise(
                    it.results.map { it.toDomain() }
                )
            }
        }
        return null
    }


    fun findByExerciseId(id: Int): Flow<Exercise> {

        return exerciseLocalDataSource.findExerciseById(id)

    }


}

