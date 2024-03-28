package com.lifebetter.simplegymapp.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM EXERCISE")
    fun getAll(): Flow<List<Exercise>>

    @Query("SELECT * FROM Exercise WHERE id = :id")
    fun findByIdExercise(id: Int): Flow<Exercise>

    @Query("SELECT COUNT(id) FROM Exercise")
    suspend fun exerciseCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(list: List<Exercise>)

}