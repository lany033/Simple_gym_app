package com.lifebetter.simplegymapp.model.datasource

import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.model.database.ExerciseDao
import kotlinx.coroutines.flow.Flow


class ExerciseLocalDataSource(private val exerciseDao: ExerciseDao) {

    val exercises: Flow<List<Exercise>> = exerciseDao.getAll()

    fun isEmpty(): Boolean = exerciseDao.exerciseCount() == 0

    fun save(exercises: List<Exercise>){
        exerciseDao.insertExercises(exercises)
    }
}