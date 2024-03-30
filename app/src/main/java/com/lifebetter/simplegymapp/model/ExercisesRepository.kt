package com.lifebetter.simplegymapp.model


import android.util.Log
import com.lifebetter.simplegymapp.model.data.RemoteExercise
import com.lifebetter.simplegymapp.model.data.toExercise
import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.model.database.MyDatabaseFactory
import com.lifebetter.simplegymapp.model.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.datasource.ExerciseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.withContext

class ExercisesRepository(myDatabaseFactory: MyDatabaseFactory) {

    private val localDataSource = ExerciseLocalDataSource(myDatabaseFactory.createExerciseDao())
    private val remoteDataSource = ExerciseRemoteDataSource()

    suspend fun popularExercises(page: Int, pageSize:Int): Flow<List<Exercise>>{
        withContext(Dispatchers.IO){
            if (localDataSource.isEmpty()){
                val exercises = remoteDataSource.getExercises(page,pageSize).mapCatching { e -> e.results }
                localDataSource.save(exercises.getOrThrow().map { it.toExercise() }.toLocalModel())
            }
        }
        val popularExercises = localDataSource.exercises()
        Log.d("log Popular exercises", popularExercises.toString())
        return popularExercises
    }

    //val popularExercises = localDataSource.exercises

    /*
    suspend fun requestPopularExercises() = withContext(Dispatchers.IO){
        if (localDataSource.isEmpty()){
            val exercises = remoteDataSource.getExercises().mapCatching { e -> e.results }
            localDataSource.save(exercises.getOrThrow().map { it.toExercise() }.toLocalModel())
        }
    }
 */

}

private fun List<RemoteExercise>.toLocalModel(): List<Exercise> = map{ it.toLocalModel() }

private fun RemoteExercise.toLocalModel(): Exercise = Exercise(
    id,
    name,
    description
)