package com.lifebetter.simplegymapp.model

import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.remotedata.ExercisesRemoteDataSource

class ExerciseRepository(
    private val localDataSource: ExerciseLocalDataSource,
    private val remoteDataSource: ExercisesRemoteDataSource,
    private val exerciseDatabase: ExerciseDatabase

) {

    //val popularExercises = localDataSource.exercises

    /*
    suspend fun requestPopularMovies(page: Int, pageSize:Int) = withContext(Dispatchers.IO){
        if (localDataSource.isEmpty()){
            val exercises = remoteDataSource.getExercises(page, pageSize)
            localDataSource.save(exercises.results.toLocalModel())
        }
    }
     */




}

