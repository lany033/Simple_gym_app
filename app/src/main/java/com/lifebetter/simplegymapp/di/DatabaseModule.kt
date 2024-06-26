package com.lifebetter.simplegymapp.di

import android.app.Application
import androidx.room.Room
import com.lifebetter.simplegymapp.framework.database.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        ExerciseDatabase::class.java,
        "workout-db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideWorkoutDao(db: ExerciseDatabase) = db.workoutDao()

    @Provides
    @Singleton
    fun provideWorkoutSessionDao(db: ExerciseDatabase) = db.workoutSessionDao()

    @Provides
    @Singleton
    fun provideExerciseDao(db: ExerciseDatabase) = db.exerciseDao()
}
