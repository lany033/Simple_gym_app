package com.lifebetter.simplegymapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Workout::class, SetWorkout::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    abstract fun setDao(): SetDao

}