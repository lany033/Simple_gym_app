package com.lifebetter.simplegymapp.ui.screens.exercises

import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.domain.Language
import com.lifebetter.simplegymapp.testrules.CoroutinesTestRule
import com.lifebetter.simplegymapp.usecases.GetPopularExercisesUseCase
import com.lifebetter.simplegymapp.usecases.RequestPopularExerciseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ExerciseViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPopularExercisesUseCase: GetPopularExercisesUseCase

    @Mock
    lateinit var requestPopularExerciseUseCase: RequestPopularExerciseUseCase

    @Mock
    lateinit var exercisesRepository: ExercisesRepository

    private lateinit var viewModel: ExerciseViewModel

    private val exercises = listOf(sampleExercise.copy(id = 1))

    @Before
    fun setUp(){
        whenever(getPopularExercisesUseCase()).thenReturn(flowOf(exercises))
        viewModel = ExerciseViewModel(exercisesRepository ,requestPopularExerciseUseCase, getPopularExercisesUseCase)
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        val results = mutableListOf<ExerciseViewModel.ExerciseListState>()
        val job = launch { viewModel.exerciseListState.toList(results) }
        runCurrent()
        job.cancel()

        assertEquals(ExerciseViewModel.ExerciseListState(exerciseList = exercises), results[0])
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

