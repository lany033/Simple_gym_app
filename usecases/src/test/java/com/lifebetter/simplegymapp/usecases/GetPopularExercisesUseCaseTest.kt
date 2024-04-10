package com.lifebetter.simplegymapp.usecases

import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.Language
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPopularExercisesUseCaseTest{
    @Test
    fun `Invoke calls exercise repository`(): Unit = runBlocking {
        val exercises = flowOf(listOf(sampleExercise.copy(id = 1)))
        val getPopularExercisesUseCase = GetPopularExercisesUseCase(mock{
                on { popularExercises } doReturn exercises
        })

        val result = getPopularExercisesUseCase()

        Assert.assertEquals(exercises, result)
    }
}


val sampleExercise = Exercise(
    name = "Squat",
    description = "squat",
    images = "",
    muscles = emptyList(),
    language = Language(full_name = "" ,id = 0, short_name = ""),
    id = 0,
    equipment = emptyList()
)