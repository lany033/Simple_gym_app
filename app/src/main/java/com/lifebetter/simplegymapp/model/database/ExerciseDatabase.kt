package com.lifebetter.simplegymapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ExerciseEntity::class],
    version = 1
)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun dao(): ExerciseDao


}

