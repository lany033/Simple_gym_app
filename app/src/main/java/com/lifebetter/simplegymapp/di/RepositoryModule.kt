package com.lifebetter.simplegymapp.di


import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.model.database.WorkoutDao
import com.lifebetter.simplegymapp.model.database.WorkoutSessionDao
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.model.database.ExerciseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(workoutDao: WorkoutDao, workoutSessionDao: WorkoutSessionDao, exerciseDao: ExerciseDao): ExercisesRepository {
        return ExercisesRepository(ExercisesRemoteDataSource(), ExerciseLocalDataSource(workoutDao, workoutSessionDao, exerciseDao))
    }

    @Provides
    @Singleton
    fun provideExerciseRemoteDataSource(): ExercisesRemoteDataSource {
        return ExercisesRemoteDataSource()
    }

}
