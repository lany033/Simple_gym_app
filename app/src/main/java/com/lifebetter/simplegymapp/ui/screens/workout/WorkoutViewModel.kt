package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.Workout as WorkoutDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {

    private val _workoutListState = MutableStateFlow(WorkoutListState())
    val workoutListState = _workoutListState.asStateFlow()

    fun onOpenAccordion() {
        _workoutListState.update {
            it.copy(openAccordion = true)
        }
    }

    fun offOpenAccordion() {
        _workoutListState.update {
            it.copy(openAccordion = false)
        }
    }

    fun deleteWorkout(workout: WorkoutDomain) {
        viewModelScope.launch {
            exercisesRepository.deleteWorkout(workout)
        }
    }

    init {
        viewModelScope.launch {
            exercisesRepository.workouts.collect { workout: List<WorkoutDomain> ->
                _workoutListState.update { WorkoutListState(workoutList = workout) }
            }
        }
    }

    data class WorkoutListState(
        val openAccordion: Boolean = true,
        val workoutList: List<WorkoutDomain> = emptyList(),
    )
}