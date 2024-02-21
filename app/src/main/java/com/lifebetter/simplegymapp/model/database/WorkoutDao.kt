package com.lifebetter.simplegymapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout")
    fun getAll(): Flow<List<Workout>>

    @Query("SELECT COUNT(id) FROM Workout")
    suspend fun workoutCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(list: List<Workout>)
}