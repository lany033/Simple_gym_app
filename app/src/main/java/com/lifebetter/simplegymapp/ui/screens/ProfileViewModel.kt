package com.lifebetter.simplegymapp.ui.screens

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository) :
    ViewModel() {

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState = _profileState.asStateFlow()

    init {
        viewModelScope.launch {
            exercisesRepository.getAllWorkoutSessions().collect { workoutSession ->
                _profileState.update {
                    it.copy(listWorkoutSession = workoutSession, workoutCount = workoutSession.size)
                }
            }
        }
    }

    data class ProfileState(
        val user: String = "",
        val listWorkoutSession: List<WorkoutSession> = emptyList(),
        val workoutCount: Int = 0
    )
}