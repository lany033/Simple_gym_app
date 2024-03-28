package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.framework.datasource.ExerciseRoomDataSource
import com.lifebetter.simplegymapp.framework.datasource.ExercisesServerDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.toDomain
import com.lifebetter.simplegymapp.domain.Workout as WorkoutDomain
import com.lifebetter.simplegymapp.domain.WorkoutSession as WorkoutSessionDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExercisesRepository @Inject constructor(
    private val exercisesServerDataSource: ExercisesServerDataSource,
    private val exerciseRoomDataSource: ExerciseRoomDataSource
) {
    val workouts = exerciseRoomDataSource.workouts

    val exercises = exerciseRoomDataSource.exercises

    suspend fun saveWorkoutSession(workoutSession: WorkoutSessionDomain) {
        exerciseRoomDataSource.saveWorkoutSession(workoutSession)
    }

    suspend fun deleteWorkoutSession(workoutSession: WorkoutSessionDomain) {
        exerciseRoomDataSource.deleteWorkoutSession(workoutSession)
    }

    fun getAllWorkoutSessions(): Flow<List<WorkoutSessionDomain>> {
        return exerciseRoomDataSource.getWorkoutSession()
    }

    suspend fun saveNewWorkout(list: List<WorkoutDomain>) {
        exerciseRoomDataSource.saveWorkout(list)
    }

    suspend fun deleteWorkout(workout: WorkoutDomain) {
        exerciseRoomDataSource.deleteWorkout(workout)
    }

    fun findByWorkoutId(id: Int): Flow<WorkoutDomain> {
        return exerciseRoomDataSource.findByWorkoutId(id)
    }

    suspend fun requestExercises(): Error? {
        if (exerciseRoomDataSource.isExerciseListEmpty()) {
            val exercisesList =
                exercisesServerDataSource.getExercises()
            exercisesList.fold(ifLeft = { return it }) {
                exerciseRoomDataSource.saveExercise(
                    it.results.map { it.toDomain() }
                )
            }
        }
        return null
    }


    fun findByExerciseId(id: Int): Flow<Exercise> {

        return exerciseRoomDataSource.findExerciseById(id)

    }


}

