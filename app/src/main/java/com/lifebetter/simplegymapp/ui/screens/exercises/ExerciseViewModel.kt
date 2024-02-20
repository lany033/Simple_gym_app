package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _equipmentId = MutableStateFlow(11)
    private val _muscleId = MutableStateFlow(11)

    private val _selectedExercises = MutableStateFlow(SnapshotStateList<Exercise>())
    val selectedExercises = _selectedExercises.asStateFlow()

    private val _showAddButton = MutableStateFlow(ExerciseListState())
    val showAddButton = _showAddButton.asStateFlow()

    private val _exerciseListState = MutableStateFlow(ExerciseListState())
    val exerciseListState = combine(
        searchText,
        _equipmentId,
        _muscleId,
        _exerciseListState
    ) { text, equipmentId, muscleId, exercises ->
        if (equipmentId == 11 && muscleId == 11 && text.isBlank()) {
            ExerciseListState(exerciseList = exercises.exerciseList)
        } else if (equipmentId != 11){
            if (text.isBlank()){
                when(equipmentId){
                    1 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBarbell())
                    2 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterBySZBar())
                    3 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByDumbbell())
                    4 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGymMat())
                    5 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterBySwissBall())
                    6 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByPullupBar())
                    7 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByNone())
                    8 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBench())
                    9 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByInclineBench())
                    10 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByKettlebell())
                    else -> ExerciseListState(exerciseList = exercises.exerciseList)
                }
            } else {
                ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                    exercise.name.uppercase().contains(text.trim().uppercase())
                })
            }
        } else if(muscleId != 11){
            if (text.isBlank()){
                when(muscleId){
                    1 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByDeltoids())
                    2 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBiceps())
                    3 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByFemor())
                    4 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGast())
                    5 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGluteus())
                    7 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByChest())
                    8 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByQuad())
                    9 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByAbd())
                    10 -> ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByTriceps())
                    else -> ExerciseListState(exerciseList = exercises.exerciseList)
                }
            } else {
                ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                    exercise.name.uppercase().contains(text.trim().uppercase())
                })
            }
        } else {
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
        Log.d("onSearch", text)
    }

    fun onFilterByEquipment(id: Int) {
        _equipmentId.value = id
    }

    fun onFilterByMuscle(id: Int) {
        _muscleId.value = id
    }

    fun onShowButtonAddExercise(id: Int) {
        viewModelScope.launch {
            _showAddButton.value = ExerciseListState(
                showButtonAddExercise = true,
                isSelected = true,
                exerciseId = id,
                exerciseSelected = exercisesRepository.findById(id)
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

    fun deleteElement(exercise: Exercise){
        viewModelScope.launch {
             _selectedExercises.value.remove(exercise)

        }
        Log.d("updateElement", _selectedExercises.value.joinToString { it.name })
    }

    init {
        Log.e("SearchViewModel - hashcode - ", "SearchViewModel - hashcode -" + this.hashCode())
        viewModelScope.launch {
            _exerciseListState.value = ExerciseListState(
                exerciseList = exercisesRepository.getExercises().results.map { it.toLocalModel() }
            )
        }
    }

    data class ExerciseListState(
        val showButtonAddExercise: Boolean = false,
        val isSelected: Boolean = false,
        val selectedItemsList: List<Exercise> = mutableListOf(),
        val exerciseId: Int = 0,
        val exerciseSelected: Exercise? = null,
        val isSearching: Boolean = false,
        val exerciseList: List<Exercise> = mutableListOf(),
        val exercisesSelectedList: List<Exercise> = mutableListOf(),
    )
}


