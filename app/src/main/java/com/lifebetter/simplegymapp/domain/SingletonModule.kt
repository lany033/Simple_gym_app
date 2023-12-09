package com.lifebetter.simplegymapp.domain

import android.content.Context
import androidx.room.Room
import com.lifebetter.simplegymapp.model.database.ExerciseDao
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun providesExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase =
        Room.databaseBuilder(context, ExerciseDatabase::class.java, "exercise-db").build()
    @Singleton
    @Provides
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase): ExerciseDao = exerciseDatabase.dao()
    @Singleton
    @Provides
    fun provideKeyDao(exerciseDatabase: ExerciseDatabase): RemoteKeysDao = exerciseDatabase.keydao()
}