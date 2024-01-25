package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ExercisesViewModel(private val exercisesRepository: ExercisesRepository): ViewModel() {
    //view
    private val _state = MutableStateFlow(ExercisesState())
    val state: StateFlow<ExercisesState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ExercisesState(exercises = exercisesRepository.getExercises().results.map {
                it.toLocalModel()
            })
        }
    }

    //interface
    data class ExercisesState(
        val exercises: List<Exercise> = emptyList()
    )
}