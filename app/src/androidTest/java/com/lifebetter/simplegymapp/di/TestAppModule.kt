package com.lifebetter.simplegymapp.di

import com.lifebetter.simplegymapp.apptestshared.FakeExerciseDao
import com.lifebetter.simplegymapp.apptestshared.FakeRemoteDataSource
import com.lifebetter.simplegymapp.apptestshared.sampleExercise
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.framework.database.ExerciseDao
import com.lifebetter.simplegymapp.framework.database.ExerciseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppDataModule::class])
object TestAppModule {
    @Provides
    @Singleton
    fun provideExerciseDao(): ExerciseDao  = FakeExerciseDao()
    @Provides
    @Singleton
    fun provideRemoteService(): ExercisesRemoteDataSource = FakeRemoteDataSource()
}