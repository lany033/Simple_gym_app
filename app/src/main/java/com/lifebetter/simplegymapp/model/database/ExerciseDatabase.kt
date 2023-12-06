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
    abstract val dao: ExerciseDao

    companion object {
        fun createDatabase(context: Context): ExerciseDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ExerciseDatabase::class.java,
                "db-test"
            ).build()
        }

        fun createExerciseDao(context: Context): ExerciseDao{
            return createDatabase(context).dao
        }

    }

}

