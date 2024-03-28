package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.fromWorkoutDomain
import com.lifebetter.simplegymapp.domain.fromWorkoutSessionDomain
import com.lifebetter.simplegymapp.model.database.Exercise
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
        exerciseLocalDataSource.saveWorkoutSession(workoutSession.fromWorkoutSessionDomain())
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSessionDomain) {
        exerciseLocalDataSource.deleteWorkoutSession(workoutSession.fromWorkoutSessionDomain())
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSession>> {
        return exerciseLocalDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<WorkoutDomain>) {
        exerciseLocalDataSource.saveWorkout(list.map { it.fromWorkoutDomain() })
    }

    suspend fun deleteWorkout(workout: WorkoutDomain) {
        exerciseLocalDataSource.deleteWorkout(workout.fromWorkoutDomain())
    }

    fun findByWorkoutId(id: Int): Flow<Workout> {
        return exerciseLocalDataSource.findByWorkoutId(id)
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

