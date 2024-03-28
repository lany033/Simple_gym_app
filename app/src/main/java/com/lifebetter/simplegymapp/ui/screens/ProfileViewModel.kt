package com.lifebetter.simplegymapp.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.WorkoutSession as WorkoutSessionDomain
import com.lifebetter.simplegymapp.domain.toWorkoutSessionDomain
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
            exercisesRepository.getAllWorkoutSessions().collect { workoutSessionList ->
                _profileState.update {
                    it.copy(
                        listWorkoutSession = workoutSessionList,
                        workoutCount = workoutSessionList.size,
                    )
                }

            }
        }
    }

    fun deleteWorkout(workoutSession: WorkoutSessionDomain){
        viewModelScope.launch {
            exercisesRepository.deleteWorkoutSession(workoutSession)
        }
        Log.d("deleteWorkout", _profileState.value.listWorkoutSession.size.toString())
    }

    data class ProfileState(
        val user: String = "",
        val listWorkoutSession: List<WorkoutSessionDomain> = emptyList(),
        val workoutCount: Int = 0,

    )
}