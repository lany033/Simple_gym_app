package com.lifebetter.simplegymapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}