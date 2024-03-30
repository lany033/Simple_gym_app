package com.lifebetter.simplegymapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM Exercise")
    fun getAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM Exercise WHERE id = :id")
    fun findById(id: Int): Flow<Exercise>

    @Query("SELECT COUNT(id) FROM Exercise")
    fun exerciseCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercise: List<Exercise>)

    @Update
    fun updateExercise(exercise: Exercise)
}