package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.mappers.toId
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.model.mappers.toTextEquipment
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _equipmentId = MutableStateFlow(0)
    val equipmentId = _equipmentId.asStateFlow()

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _exerciseList = MutableStateFlow(ExerciseListState())
    /*
    val exerciseList = combine(searchText,equipmentId,_exerciseList){text, id, exercises ->
        if (text.isBlank()) {
            ExerciseListState(exerciseList = exercises.exerciseList)
        } else if (id == 1){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Barbell")
            })
        }
        else {
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.name.uppercase().contains(text.trim().uppercase())
            })
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _exerciseList.value
    )

     */

    val exerciseList = searchText.combine(_exerciseList) { text, exercises  ->
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
        initialValue = _exerciseList.value
    )

    val exerciseListByEquipment = equipmentId.combine(_exerciseList){id, exercises ->
        if (id == 1){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
            exercise.equipment.toTextEquipment().contains("Barbell")
            })
        } else {
            ExerciseListState(exerciseList = exercises.exerciseList)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _exerciseList.value
    )


    /*
    private val _searchByEquipment = MutableStateFlow(ExerciseListState())
    val searchByEquipment = _equipment.combine(_exerciseList){ id, exercise ->
        if ( id == 1){
            ExerciseListState(exerciseList = exercise.exerciseList.filter { exercise ->
                exercise.equipment.toId().equals(1)
            })
        } else {
            ExerciseListState(exerciseList = exercise.exerciseList)
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _exerciseList.value
    )

     */
    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _isVisible.value = true
        Log.d("onSearch", text)
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange(" ")
        }
    }

    fun onFilterByEquipment(id: Int) {
        _equipmentId.value = id
        _isVisible.value = false
        Log.d("id",_equipmentId.value.toString())
    }

    init {
        viewModelScope.launch { _exerciseList.value =
            ExerciseListState(exerciseList = exercisesRepository.getExercises().results.map {
                it.toLocalModel()
            })
        }
    }

    data class ExerciseListState(
        val isVisible: Boolean = false,
        val exerciseList: List<Exercise> = emptyList(),

    )
}



/*



 */