package com.lifebetter.simplegymapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExerciseEntity::class],
    version = 1
)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract val dao: ExerciseDao
}