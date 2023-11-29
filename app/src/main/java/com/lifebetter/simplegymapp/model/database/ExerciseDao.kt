package com.lifebetter.simplegymapp.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exerciseEntity")
    fun getAll(): List<ExerciseEntity>


    @Upsert
    suspend fun upsertAll(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM Exerciseentity")
    fun pagingSource(): PagingSource<Int, ExerciseEntity>

    @Query("DELETE FROM Exerciseentity")
    suspend fun clearAll()

    @Query("SELECT COUNT(id) FROM ExerciseEntity")
    fun ExerciseCount():Int



}

