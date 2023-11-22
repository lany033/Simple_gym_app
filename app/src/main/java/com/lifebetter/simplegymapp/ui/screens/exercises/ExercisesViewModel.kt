package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.Exercise
import com.lifebetter.simplegymapp.data.ExercisePagingSource
import com.lifebetter.simplegymapp.data.toExercise
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.lifebetter.simplegymapp.model.ExercisesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExercisesViewModel(private val exercisesRepository: ExercisesRepository): ViewModel() {

    val exercisePager = Pager(PagingConfig(pageSize = 20)){
        ExercisePagingSource(exercisesRepository)
    }.flow.cachedIn(viewModelScope)

    /*

    //view
    private val _state = MutableStateFlow(ExercisesState())
    val state: StateFlow<ExercisesState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ExercisesState(exercises = exercisesRepository.getExercises().results.map {
                it.toExercise()
            })
        }
    }

    //interface
    data class ExercisesState(
        val exercises: List<Exercise> = emptyList()
    )

     */
}

