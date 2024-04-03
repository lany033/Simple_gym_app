package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.framework.toError
import com.lifebetter.simplegymapp.usecases.GetPopularExercisesUseCase
import com.lifebetter.simplegymapp.usecases.RequestPopularExerciseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: Arreglar los copy con update
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository,
    private val requestPopularExerciseUseCase: RequestPopularExerciseUseCase,
    getPopularExercisesUseCase: GetPopularExercisesUseCase
) : ViewModel() {
    private val _openAlertDialog = MutableStateFlow(false)
    val openAlertDialog = _openAlertDialog.asStateFlow()

    private val _nameWorkout = MutableStateFlow("")
    val nameWorkout = _nameWorkout.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _selectedExercises = MutableStateFlow(SnapshotStateList<com.lifebetter.simplegymapp.domain.Exercise>())
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
            exercisesRepository.findByExerciseId(id).collect{ exercise ->
                _showAddButton.value = ExerciseListState(
                    showButtonAddExercise = true,
                    exerciseId = id,
                    exerciseSelected = exercise
                )
            }
        }
    }

    fun offShowButtonExercise() {
        viewModelScope.launch {
            _showAddButton.value = ExerciseListState(
                showButtonAddExercise = false
            )
        }
    }

    fun deleteElement(exercise: com.lifebetter.simplegymapp.domain.Exercise) {
        viewModelScope.launch {
            _selectedExercises.value.remove(exercise)
        }
    }

    fun onSaveRoutine() {
        viewModelScope.launch {
            val newWorkout = com.lifebetter.simplegymapp.domain.Workout(
                nameWorkout = _nameWorkout.value,
                exerciseList = _selectedExercises.value
            )
            exercisesRepository.saveNewWorkout(newWorkout)
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

        viewModelScope.launch {

            requestPopularExerciseUseCase()

            getPopularExercisesUseCase()
                .catch { cause ->
                    _exerciseListState.update { it.copy(error = cause.toError()) }
                    Log.d("error", cause.toError().toString())
                }
                .collect{exercises -> _exerciseListState.update { it.copy(exerciseList = exercises) }
            }
        }
        Log.e("SearchViewModel - hashcode - ", "SearchViewModel - hashcode -" + this.hashCode())
        //Log.d("error", _exerciseListState.value.error.toString())

    }

    data class ExerciseListState(
        val showButtonAddExercise: Boolean = false,
        val exerciseId: Int = 0,
        val exerciseSelected: com.lifebetter.simplegymapp.domain.Exercise? = null,
        val isSearching: Boolean = false,
        val exerciseList: List<com.lifebetter.simplegymapp.domain.Exercise> = mutableListOf(),
        val error: com.lifebetter.simplegymapp.domain.Error? = null,
        val workout: com.lifebetter.simplegymapp.domain.Workout? = null
    )
}


