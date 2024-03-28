package com.lifebetter.simplegymapp.di


import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.framework.datasource.ExerciseRoomDataSource
import com.lifebetter.simplegymapp.framework.database.WorkoutDao
import com.lifebetter.simplegymapp.framework.database.WorkoutSessionDao
import com.lifebetter.simplegymapp.framework.datasource.ExercisesServerDataSource
import com.lifebetter.simplegymapp.framework.database.ExerciseDao
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
        return ExercisesRepository(ExercisesServerDataSource(), ExerciseRoomDataSource(workoutDao, workoutSessionDao, exerciseDao))
    }

    @Provides
    @Singleton
    fun provideExerciseRemoteDataSource(): ExercisesServerDataSource {
        return ExercisesServerDataSource()
    }

}
