package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.Workout
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

    //private val _openAccordion = MutableStateFlow(false)
    //val openAccordion = _openAccordion.asStateFlow()

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

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            exercisesRepository.deleteWorkout(workout)
        }
    }

    init {
        viewModelScope.launch {
            exercisesRepository.workouts.collect { workout: List<Workout> ->
                _workoutListState.update { WorkoutListState(workoutList = workout) }
            }
        }
    }

    data class WorkoutListState(
        val isloading: Boolean = false,
        val openAccordion: Boolean = false,
        val workoutList: List<Workout> = emptyList(),
    )
}