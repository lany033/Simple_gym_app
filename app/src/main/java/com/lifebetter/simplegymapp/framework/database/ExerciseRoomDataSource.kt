package com.lifebetter.simplegymapp.framework.database

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.Workout
import com.lifebetter.simplegymapp.domain.WorkoutSession
import com.lifebetter.simplegymapp.domain.tryCall
import com.lifebetter.simplegymapp.framework.database.ExerciseDao
import com.lifebetter.simplegymapp.framework.database.WorkoutDao
import com.lifebetter.simplegymapp.framework.database.WorkoutSessionDao
import com.lifebetter.simplegymapp.domain.fromLocalModel
import com.lifebetter.simplegymapp.domain.fromWorkoutDomain
import com.lifebetter.simplegymapp.domain.fromWorkoutSessionDomain
import com.lifebetter.simplegymapp.domain.toExerciseDomain
import com.lifebetter.simplegymapp.domain.toLocalModel
import com.lifebetter.simplegymapp.domain.toWorkoutDomain
import com.lifebetter.simplegymapp.domain.toWorkoutSessionDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRoomDataSource @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutSessionDao: WorkoutSessionDao,
    private val exerciseDao: ExerciseDao
) : ExerciseLocalDataSource {
    override val workouts: Flow<List<Workout>> = workoutDao.getAll().map { it.toWorkoutDomain() }

    override val exercises: Flow<List<Exercise>> = exerciseDao.getAll().map { it.toLocalModel() }

    override suspend fun isExerciseListEmpty(): Boolean = exerciseDao.exerciseCount() == 0

    override suspend fun saveExercise(list: List<Exercise>): Error? = tryCall {
        exerciseDao.insertExercise(list.fromLocalModel())
    }.fold(ifLeft = { it }, ifRight = { null })

    override fun findExerciseById(id: Int): Flow<Exercise> =
        exerciseDao.findByIdExercise(id).map { it.toExerciseDomain() }

    override fun findByWorkoutId(id: Int): Flow<Workout> =
        workoutDao.findById(id).map { it.toWorkoutDomain() }

    override suspend fun deleteWorkout(workout: Workout) =
        workoutDao.deleteWorkout(workout.fromWorkoutDomain())

    override suspend fun saveWorkout(list: List<Workout>) {
        workoutDao.insertWorkout(list.fromWorkoutDomain())
    }

    override suspend fun saveWorkoutSession(workoutSession: WorkoutSession) {
        workoutSessionDao.insertWorkoutSession(workoutSession.fromWorkoutSessionDomain())
    }

    override fun getWorkoutSession(): Flow<List<WorkoutSession>> {
        return workoutSessionDao.getAll().map { it.toWorkoutSessionDomain() }
    }

    override suspend fun deleteWorkoutSession(workoutSession: WorkoutSession) =
        workoutSessionDao.deleteWorkoutSession(workoutSession.fromWorkoutSessionDomain())


}