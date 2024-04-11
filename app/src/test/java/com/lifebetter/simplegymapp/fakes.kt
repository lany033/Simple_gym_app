package com.lifebetter.simplegymapp

import arrow.core.Either
import arrow.core.right
import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Error
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.ExerciseResult
import com.lifebetter.simplegymapp.domain.Workout
import com.lifebetter.simplegymapp.domain.WorkoutSession
import com.lifebetter.simplegymapp.ui.screens.exercises.sampleExercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

val defaultFakeExercises = listOf(
    sampleExercise.copy(id = 1),
    sampleExercise.copy(id = 2),
    sampleExercise.copy(id = 3),
    sampleExercise.copy(id = 4)
)

class FakeLocalDataSource : ExerciseLocalDataSource {

    val inMemoryExercise = MutableStateFlow<List<Exercise>>(emptyList())
    val inMemoryWorkout = MutableStateFlow<List<Workout>>(emptyList())


    override val workouts = inMemoryWorkout

    override val exercises = inMemoryExercise

    private lateinit var findExerciseFlow: MutableStateFlow<Exercise>
    override suspend fun isExerciseListEmpty(): Boolean {
        return exercises.value.isEmpty()
    }
    override suspend fun saveExercise(list: List<Exercise>): Error? {
        inMemoryExercise.value = list

        if (::findExerciseFlow.isInitialized){
            list.firstOrNull(){ it.id == findExerciseFlow.value.id }?.let { findExerciseFlow.value = it }
        }

        return null
    }
    override fun findExerciseById(id: Int): Flow<Exercise> {
        findExerciseFlow = MutableStateFlow(inMemoryExercise.value.first { it.id == id })
        return findExerciseFlow
    }

    override fun findByWorkoutId(id: Int): Flow<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun saveWorkoutSession(workoutSession: WorkoutSession) {
        TODO("Not yet implemented")
    }

    override fun getWorkoutSession(): Flow<List<WorkoutSession>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkoutSession(workoutSession: WorkoutSession) {
        TODO("Not yet implemented")
    }

}

class FakeRemoteDataSource : ExercisesRemoteDataSource {

    var exercises = defaultFakeExercises
    override suspend fun getExercises() = exercises.right()

}