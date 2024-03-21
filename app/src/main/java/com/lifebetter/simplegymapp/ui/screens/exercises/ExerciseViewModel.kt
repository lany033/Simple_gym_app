package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: Arreglar los updates con copy
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {
    private val _openAlertDialog = MutableStateFlow(false)
    val openAlertDialog = _openAlertDialog.asStateFlow()

    private val _workout = MutableStateFlow(mutableListOf<Workout>())
    val workout = _workout.asStateFlow()

    private val _nameWorkout = MutableStateFlow("")
    val nameWorkout = _nameWorkout.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()


    private val _selectedExercises = MutableStateFlow(SnapshotStateList<Exercise>())
    val selectedExercises = _selectedExercises.asStateFlow()

    private val _showAddButton = MutableStateFlow(ExerciseListState())
    val showAddButton = _showAddButton.asStateFlow()

    private val _exerciseListState = MutableStateFlow(ExerciseListState())
    val exerciseListState = searchText.combine(_exerciseListState) { text, exercises  ->
        if (text.isBlank()) {
            ExerciseListState(exerciseList = exercises.exerciseList)
        }
        else {
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.name.uppercase().contains(text.trim().uppercase())
            })
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _exerciseListState.value
    )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onNameText(text: String) {
        _nameWorkout.value = text
    }

    fun onShowButtonAddExercise(id: Int) {
        viewModelScope.launch {
            _showAddButton.value = ExerciseListState(
                showButtonAddExercise = true,
                isSelected = true,
                exerciseId = id,
                exerciseSelected = exercisesRepository.findByExerciseId(id)
            )
        }
    }

    fun offShowButtonExercise() {
        viewModelScope.launch {
            _showAddButton.value = ExerciseListState(
                showButtonAddExercise = false
            )
        }
    }

    fun deleteElement(exercise: Exercise) {
        viewModelScope.launch {
            _selectedExercises.value.remove(exercise)
        }
    }

    fun onSaveRoutine() {
        viewModelScope.launch {
            _workout.value.add(
                Workout(
                    nameWorkout = _nameWorkout.value,
                    exerciseList = _selectedExercises.value
                )
            )
            exercisesRepository.saveNewWorkout(_workout.value)
            _workout.value.clear()
            _selectedExercises.value.clear()
            _nameWorkout.value = ""
            _openAlertDialog.value = false
        }
    }

    fun openAlertDialog() {
        _openAlertDialog.value = true
    }

    fun closeAlertDialog() {
        _openAlertDialog.value = false
    }

    init {
        Log.e("SearchViewModel - hashcode - ", "SearchViewModel - hashcode -" + this.hashCode())
        viewModelScope.launch {
            _exerciseListState.value = ExerciseListState(
                exerciseList = exercisesRepository.requestExercises()
            )
        }
    }

    data class ExerciseListState(
        val showButtonAddExercise: Boolean = false,
        val isSelected: Boolean = false,
        val exerciseId: Int = 0,
        val exerciseSelected: Exercise? = null,
        val isSearching: Boolean = false,
        val exerciseList: List<Exercise> = mutableListOf(),
        val exercisesSelectedList: List<Exercise> = mutableListOf(),
    )
}


