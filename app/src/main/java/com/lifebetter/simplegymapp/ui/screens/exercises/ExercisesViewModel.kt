package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.Exercise
import com.lifebetter.simplegymapp.data.getExercises
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExercisesViewModel(): ViewModel() {
    //view
    private val _state = MutableStateFlow(ExercisesState())
    val state: StateFlow<ExercisesState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = ExercisesState(exercises = getExercises())
            Log.d("viewmodel,","asi paso por el init jio jio")

        }
    }

    //interface
    data class ExercisesState(
        val exercises: List<Exercise> = emptyList()
    )
}

