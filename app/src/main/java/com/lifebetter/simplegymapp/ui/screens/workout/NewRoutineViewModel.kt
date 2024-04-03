package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: Eliminar
@HiltViewModel
class NewRoutineViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository): ViewModel() {

    private val _newRoutineState = MutableStateFlow(NewRoutineState())
    val newRoutineState = _newRoutineState.asStateFlow()

    init {
        viewModelScope.launch {
            exercisesRepository.workouts.collect{ workouts ->
                _newRoutineState.update {
                    it.copy(workoutList = workouts)
                }
            }

        }
    }

    data class NewRoutineState(
        val openAlertDialog: Boolean = false,
        val nameWorkout: String = "",
        val workoutList: List<com.lifebetter.simplegymapp.domain.Workout> = emptyList(),
    )
}