package com.lifebetter.simplegymapp.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SetDao {
    @Query("SELECT * FROM SetWorkout")
    fun getAll(): Flow<List<SetWorkout>>

    @Query("SELECT COUNT(setId) FROM SetWorkout")
    suspend fun setCount(): Int

    @Query("SELECT * FROM SetWorkout WHERE setId = :id")
    fun findById(id: Int): Flow<SetWorkout>

    @Delete
    suspend fun deleteWorkout(setWorkout: SetWorkout)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(list: List<SetWorkout>)
}