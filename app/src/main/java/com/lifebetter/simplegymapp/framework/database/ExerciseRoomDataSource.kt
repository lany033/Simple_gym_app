package com.lifebetter.simplegymapp.framework.database

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.framework.fromLocalModel
import com.lifebetter.simplegymapp.framework.fromWorkoutDomain
import com.lifebetter.simplegymapp.framework.fromWorkoutSessionDomain
import com.lifebetter.simplegymapp.framework.toExerciseDomain
import com.lifebetter.simplegymapp.framework.toLocalModel
import com.lifebetter.simplegymapp.framework.toWorkoutDomain
import com.lifebetter.simplegymapp.framework.toWorkoutSessionDomain
import com.lifebetter.simplegymapp.framework.tryCall
import com.lifebetter.simplegymapp.domain.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRoomDataSource @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutSessionDao: WorkoutSessionDao,
    private val exerciseDao: ExerciseDao
) : ExerciseLocalDataSource {
    override val workouts: Flow<List<com.lifebetter.simplegymapp.domain.Workout>> = workoutDao.getAll().map { it.toWorkoutDomain() }

    override val exercises: Flow<List<com.lifebetter.simplegymapp.domain.Exercise>> = exerciseDao.getAll().map { it.toLocalModel() }

    override suspend fun isExerciseListEmpty(): Boolean = exerciseDao.exerciseCount() == 0

    override suspend fun saveExercise(list: List<com.lifebetter.simplegymapp.domain.Exercise>): Error? = tryCall {
        exerciseDao.insertExercise(list.fromLocalModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override fun findExerciseById(id: Int): Flow<com.lifebetter.simplegymapp.domain.Exercise> =
        exerciseDao.findByIdExercise(id).map { it.toExerciseDomain() }

    override fun findByWorkoutId(id: Int): Flow<com.lifebetter.simplegymapp.domain.Workout> =
        workoutDao.findById(id).map { it.toWorkoutDomain() }

    override suspend fun deleteWorkout(workout: com.lifebetter.simplegymapp.domain.Workout) =
        workoutDao.deleteWorkout(workout.fromWorkoutDomain())

    override suspend fun saveWorkout(workout: com.lifebetter.simplegymapp.domain.Workout) {
        workoutDao.insertWorkout(workout.fromWorkoutDomain())
    }

    override suspend fun saveWorkoutSession(workoutSession: com.lifebetter.simplegymapp.domain.WorkoutSession) {
        workoutSessionDao.insertWorkoutSession(workoutSession.fromWorkoutSessionDomain())
    }

    override fun getWorkoutSession(): Flow<List<com.lifebetter.simplegymapp.domain.WorkoutSession>> {
        return workoutSessionDao.getAll().map { it.toWorkoutSessionDomain() }
    }

    override suspend fun deleteWorkoutSession(workoutSession: com.lifebetter.simplegymapp.domain.WorkoutSession) =
        workoutSessionDao.deleteWorkoutSession(workoutSession.fromWorkoutSessionDomain())


}