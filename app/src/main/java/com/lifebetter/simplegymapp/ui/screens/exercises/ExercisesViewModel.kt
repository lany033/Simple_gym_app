package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.data.RemoteExercise
import com.lifebetter.simplegymapp.model.data.toExercise
import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.model.datasource.ExerciseRemoteDataSource
import com.lifebetter.simplegymapp.ui.DefaultPaginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ExercisesViewModel(private val exercisesRepository: ExercisesRepository): ViewModel() {

    //view
    private var _state = MutableStateFlow(ExerciseState())
    var state: StateFlow<ExerciseState> = _state.asStateFlow()



    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            Result.success(exercisesRepository.popularExercises)
        },
        getNextKey = {
            _state.value.page + 20
        },
        onError = {
            _state.value = _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { exercise, newKey ->
            _state.value = _state.value.copy(
                exercise = _state.value.exercise,
                page = newKey,
                endReached = exercise.equals("")
            )
        }
    )

    init {
        requestExercises()
    }

    fun loadNextItem(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun requestExercises(){
        viewModelScope.launch {
            exercisesRepository.requestPopularExercises()
        }
    }


    data class ExerciseState(
        val isLoading: Boolean = false,
        val exercise: List<Exercise> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 0
    )

}

