package com.lifebetter.simplegymapp.model


import com.lifebetter.simplegymapp.model.remotedata.ExerciseService
import com.lifebetter.simplegymapp.model.remotedata.RemoteConnection
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
    fun provideMainRepository(

    ): ExercisesRepository {
        return ExercisesRepository()
    }

}