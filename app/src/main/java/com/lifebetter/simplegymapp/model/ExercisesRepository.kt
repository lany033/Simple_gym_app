package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.App
import com.lifebetter.simplegymapp.model.data.RemoteExercise
import com.lifebetter.simplegymapp.model.data.toExercise
import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.model.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.datasource.ExerciseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExercisesRepository(application: App) {

    private val localDataSource = ExerciseLocalDataSource(application.db.exerciseDao())
    private val remoteDataSource = ExerciseRemoteDataSource(0,20)

    val popularExercises = localDataSource.exercises

    suspend fun requestPopularExercises() = withContext(Dispatchers.IO){
        if (localDataSource.isEmpty()){
            val exercises = remoteDataSource.getExercises().mapCatching { e -> e.results }
            localDataSource.save(exercises.getOrThrow().map { it.toExercise() }.toLocalModel())
        }
    }

}

private fun List<RemoteExercise>.toLocalModel(): List<Exercise> = map{ it.toLocalModel() }

private fun RemoteExercise.toLocalModel(): Exercise = Exercise(
    id,
    name,
    description
)