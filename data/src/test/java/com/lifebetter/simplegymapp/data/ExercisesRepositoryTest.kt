package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.data.datasource.ExerciseLocalDataSource
import com.lifebetter.simplegymapp.data.datasource.ExercisesRemoteDataSource
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.Language
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ExercisesRepositoryTest {
    @Mock
    lateinit var exercisesRemoteDataSource: ExercisesRemoteDataSource

    @Mock
    lateinit var exerciseLocalDataSource: ExerciseLocalDataSource

    @Mock
    lateinit var exercisesRepository: ExercisesRepository

    val localExercises = flowOf(listOf(sampleExercise.copy(id = 1)))

    @Before
    fun setUp() {
        whenever(exerciseLocalDataSource.exercises).thenReturn(localExercises)
        exercisesRepository = ExercisesRepository(exercisesRemoteDataSource, exerciseLocalDataSource)
    }

    @Test
    fun `Exercises are taken from local data source if available`(): Unit = runBlocking {

        val result = exercisesRepository.popularExercises

        assertEquals(localExercises, result)
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