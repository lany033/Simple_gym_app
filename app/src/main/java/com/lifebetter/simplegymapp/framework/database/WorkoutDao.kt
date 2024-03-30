package com.lifebetter.simplegymapp.framework.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lifebetter.simplegymapp.domain.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM Workout")
    fun getAll(): Flow<List<Workout>>

    @Query("SELECT COUNT(id) FROM Workout")
    suspend fun workoutCount(): Int

    @Query("SELECT * FROM Workout WHERE id = :id")
    fun findById(id: Int): Flow<Workout>

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)
}