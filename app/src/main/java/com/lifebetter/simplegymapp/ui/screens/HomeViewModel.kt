package com.lifebetter.simplegymapp.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.SetWorkout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        getWorkoutSessions()
    }

    private fun getWorkoutSessions() {
        viewModelScope.launch {
            exercisesRepository.getAllWorkoutSessions().collect { workoutSessionList ->
                _homeState.update {
                    it.copy(listWorkoutSession = workoutSessionList)
                }
            }
        }
    }

    data class HomeState(
        val listWorkoutSession: List<WorkoutSession> = emptyList()
    )
}