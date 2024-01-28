package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.remotedata.muscle.MuscleDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MuscleViewModel(private val exercisesRepository: ExercisesRepository): ViewModel() {
    private val _muscleState = MutableStateFlow(MuscleState())
    val muscleState: StateFlow<MuscleState> = _muscleState.asStateFlow()

    init {
        viewModelScope.launch {
            _muscleState.value = MuscleState(muscle = exercisesRepository.getMuscles().results)
        }
    }

    data class MuscleState(
        val muscle: List<MuscleDto> = emptyList()
    )
}