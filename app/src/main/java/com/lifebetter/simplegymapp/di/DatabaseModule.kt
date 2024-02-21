package com.lifebetter.simplegymapp.di

import android.app.Application
import androidx.room.Room
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
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
    ).build()

    @Provides
    @Singleton
    fun provideWorkoutDao(db: ExerciseDatabase) = db.workoutDao()
}