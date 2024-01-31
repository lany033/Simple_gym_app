package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.model.mappers.toTextEquipment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _equipmentId = MutableStateFlow(11)
    val equipmentId = _equipmentId.asStateFlow()

    private val _muscleId = MutableStateFlow(3)


    private val _exerciseList = MutableStateFlow(ExerciseListState())
    val exerciseList = combine(searchText,equipmentId,_muscleId,_exerciseList){text, equipmentId,muscleId, exercises ->
        if (equipmentId == 11 && muscleId == 3 && text.isBlank()){
            ExerciseListState(exerciseList = exercises.exerciseList)
        } else if (equipmentId == 1){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Barbell")
            })
        } else if (equipmentId == 2){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("SZ-Bar")
            })
        } else if (equipmentId == 3){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Dumbbell")
            })
        } else if (equipmentId == 4){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Gym mat")
            })
        } else if (equipmentId == 5){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Swiss Ball")
            })
        } else if (equipmentId == 6){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Pull-up bar")
            })
        } else if (equipmentId == 7){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("none (bodyweight exercise)")
            })
        }else if (equipmentId == 8){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Bench")
            })
        }else if (equipmentId == 9){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Incline bench")
            })
        }else if (equipmentId == 10){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.equipment.toTextEquipment().contains("Kettlebell")
            })
        } else if (muscleId == 1){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Biceps brachii")
            })
        } else if (muscleId == 2){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Anterior deltoid")
            })
        } else if (muscleId == 4){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Pectoralis major")
            })
        } else if (muscleId == 5){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Triceps brachii")
            })
        } else if (muscleId == 6){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Rectus abdominis")
            })
        } else if (muscleId == 7){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Gastrocnemius")
            })
        }else if (muscleId == 8){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Gluteus maximus")
            })
        }else if (muscleId == 10){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Quadriceps femoris")
            })
        }else if (muscleId == 11){
            ExerciseListState(exerciseList = exercises.exerciseList.filter { exercise ->
                exercise.muscles.toText().contains("Biceps femoris")
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

    fun onSearchTextChange(text: String) {
        _searchText.value = text
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
        Log.d("id",_equipmentId.value.toString())
    }

    fun onFilterByMuscle(id: Int) {
        _muscleId.value = id
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

        val exerciseList: List<Exercise> = emptyList(),

    )
}


