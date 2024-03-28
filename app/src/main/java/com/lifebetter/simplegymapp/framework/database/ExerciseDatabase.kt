package com.lifebetter.simplegymapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Workout::class, WorkoutSession::class, Exercise::class], version = 19, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    abstract fun workoutSessionDao(): WorkoutSessionDao

    abstract fun exerciseDao(): ExerciseDao

}