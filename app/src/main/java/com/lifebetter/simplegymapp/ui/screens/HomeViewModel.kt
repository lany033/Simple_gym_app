package com.lifebetter.simplegymapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.WorkoutSession
import com.lifebetter.simplegymapp.domain.toWorkoutSessionDomain
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
            _homeState.value = _homeState.value.copy(isLoading = true)
            exercisesRepository.getAllWorkoutSessions().collect { workoutSessionList ->
                _homeState.update {
                    it.copy(listWorkoutSession = workoutSessionList)
                }
            }
            _homeState.value = _homeState.value.copy(isLoading = false)

        }
    }

    data class HomeState(
        val listWorkoutSession: List<WorkoutSession> = emptyList(),
        val isLoading: Boolean = false
    )
}