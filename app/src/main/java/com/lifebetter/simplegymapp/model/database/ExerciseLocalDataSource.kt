package com.lifebetter.simplegymapp.model.database

import com.lifebetter.simplegymapp.domain.Exercise
import kotlinx.coroutines.flow.Flow

class ExerciseLocalDataSource(private val exerciseDao: ExerciseDao) {


    val exercises: List<ExerciseEntity> = exerciseDao.getAll()


    fun isEmpty(): Boolean = exerciseDao.ExerciseCount() == 0

    suspend fun save(exercises: List<ExerciseEntity>){
        exerciseDao.upsertAll(exercises)
    }

}