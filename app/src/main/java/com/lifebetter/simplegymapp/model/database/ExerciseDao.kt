package com.lifebetter.simplegymapp.model.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExerciseDao {

    @Upsert
    suspend fun upsertAll(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM exerciseentity")
    fun pagingSource()

    @Query("DELETE FROM exerciseentity")
    suspend fun clearAll()

}