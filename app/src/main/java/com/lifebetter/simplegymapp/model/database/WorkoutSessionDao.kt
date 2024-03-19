package com.lifebetter.simplegymapp.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {
    @Query("SELECT * FROM WorkoutSession")
    fun getAll(): Flow<List<WorkoutSession>>

    @Query("SELECT COUNT(workoutSessionId) FROM WorkoutSession")
    suspend fun setCount(): Int

    @Query("SELECT * FROM WorkoutSession WHERE workoutSessionId = :id")
    fun findById(id: Int): Flow<WorkoutSession>

    @Delete
    suspend fun deleteWorkoutSession(workoutSession: WorkoutSession)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutSession(workoutSession: WorkoutSession)
}