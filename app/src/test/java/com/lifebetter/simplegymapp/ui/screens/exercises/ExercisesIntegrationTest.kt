package com.lifebetter.simplegymapp.ui.screens.exercises

import app.cash.turbine.test
import com.lifebetter.simplegymapp.apptestshared.FakeLocalDataSource
import com.lifebetter.simplegymapp.apptestshared.FakeRemoteDataSource
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.testrules.CoroutinesTestRule
import com.lifebetter.simplegymapp.usecases.GetPopularExercisesUseCase
import com.lifebetter.simplegymapp.usecases.RequestPopularExerciseUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExercisesIntegrationTest {


    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = listOf(sampleExercise.copy(id = 10), sampleExercise.copy(id = 11))
        val remoteData = listOf(sampleExercise.copy(id = 1), sampleExercise.copy(id = 2))
        val vm = buildViewModelWith( localData, remoteData)

        vm.exerciseListState.test {
            Assert.assertEquals(ExerciseViewModel.ExerciseListState(), awaitItem())
            Assert.assertEquals(ExerciseViewModel.ExerciseListState(exerciseList = localData), awaitItem())
            cancel()
        }
    }

    private fun buildViewModelWith(
        localData: List<Exercise>,
        remoteData: List<Exercise>
    ): ExerciseViewModel {
        val exercisesRepository = buildRepositoryWith(localData,remoteData)
        val getPopularExercisesUseCase = GetPopularExercisesUseCase(exercisesRepository)
        val requestPopularExerciseUseCase = RequestPopularExerciseUseCase(exercisesRepository)
        return ExerciseViewModel(exercisesRepository, requestPopularExerciseUseCase,getPopularExercisesUseCase)
    }

    private fun buildRepositoryWith(
        localData: List<Exercise>,
        remoteData: List<Exercise>
    ): ExercisesRepository {
        val localDataSource = FakeLocalDataSource().apply { inMemoryExercise.value = localData }
        val remoteDataSource = FakeRemoteDataSource().apply { exercises = remoteData }
        return ExercisesRepository(remoteDataSource,localDataSource)
    }
}