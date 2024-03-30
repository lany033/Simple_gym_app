package com.lifebetter.simplegymapp.di

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.framework.database.ExerciseRoomDataSource
import com.lifebetter.simplegymapp.framework.server.ExercisesServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    abstract fun bindRemoteDataSource(exercisesServerDataSource: ExercisesServerDataSource): ExercisesRemoteDataSource
    @Binds
    abstract fun bindLocalDataSource(exercisesRoomDataSource: ExerciseRoomDataSource): ExerciseLocalDataSource
}