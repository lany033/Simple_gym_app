package com.lifebetter.simplegymapp.usecases

import com.lifebetter.simplegymapp.data.ExercisesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RequestPopularExerciseUseCaseTest{
    @Test
    fun `Invoke calls exercise repository`(): Unit = runBlocking {
        val exercisesRepository = mock<ExercisesRepository>()
        val requestPopularExerciseUseCase = RequestPopularExerciseUseCase(exercisesRepository)

        requestPopularExerciseUseCase()

        verify(exercisesRepository).requestExercises()
    }
}