package com.lifebetter.simplegymapp.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {

    private val _workoutListState = MutableStateFlow(WorkoutListState())
    val workoutListState = _workoutListState.asStateFlow()

    fun onShowButtonAddExercise(id: Int) {
        Log.d("onShowButtonAddExercise", id.toString())
        viewModelScope.launch {
            _workoutListState.value = WorkoutListState(showButtonAddExercise = true, exerciseId = id)
        }
    }

    fun onAddListExercises(id:Int){
        Log.d("onShowButtonAddExercise", id.toString())
        viewModelScope.launch{
            _workoutListState.value = WorkoutListState(exerciseId = id)
        }
    }

    data class WorkoutListState(
        val showButtonAddExercise: Boolean = false,
        val exerciseId: Int? = 0,
        val exerciseList: List<Exercise> = emptyList(),
        val workoutList: List<Workout> = emptyList(),
        val navigateTo: String = ""
    )
}