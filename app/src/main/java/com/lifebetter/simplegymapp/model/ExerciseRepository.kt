package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.model.database.ExerciseDao
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import com.lifebetter.simplegymapp.model.database.ExerciseLocalDataSource

import com.lifebetter.simplegymapp.model.database.MyDatabaseFactory
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto
import com.lifebetter.simplegymapp.model.remotedata.ExerciseService
import com.lifebetter.simplegymapp.model.remotedata.ExercisesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(
    private val localDataSource: ExerciseLocalDataSource,
    private val remoteDataSource: ExercisesRemoteDataSource
) {

    val popularExercises = localDataSource.exercises

    /*
    suspend fun requestPopularMovies(page: Int, pageSize:Int) = withContext(Dispatchers.IO){
        if (localDataSource.isEmpty()){
            val exercises = remoteDataSource.getExercises(page, pageSize)
            localDataSource.save(exercises.results.toLocalModel())
        }
    }

     */


}

