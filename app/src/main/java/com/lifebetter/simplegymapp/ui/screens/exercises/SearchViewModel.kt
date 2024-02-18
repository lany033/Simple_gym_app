package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import com.lifebetter.simplegymapp.ui.screens.WorkoutViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _equipmentId = MutableStateFlow(11)
    private val _muscleId = MutableStateFlow(3)

    private val _workoutListState = MutableStateFlow(ExerciseListState())
    val workoutListState = _workoutListState.asStateFlow()

    private val _exerciseListState = MutableStateFlow(ExerciseListState())
    val exerciseListState = combine(searchText,_equipmentId,_muscleId,_exerciseListState){ text, equipmentId, muscleId, exercises ->
        if (equipmentId == 11 && muscleId == 3 && text.isBlank()){
            ExerciseListState(exerciseList = exercises.exerciseList)
        } else if (equipmentId == 1){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBarbell())
        } else if (equipmentId == 2){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterBySZBar())
        } else if (equipmentId == 3){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByDumbbell())
        } else if (equipmentId == 4){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGymMat())
        } else if (equipmentId == 5){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterBySwissBall())
        } else if (equipmentId == 6){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByPullupBar())
        } else if (equipmentId == 7){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByNone())
        }else if (equipmentId == 8){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBench())
        }else if (equipmentId == 9){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByInclineBench())
        }else if (equipmentId == 10){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByKettlebell())
        } else if (muscleId == 1){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByBiceps())
        } else if (muscleId == 2){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByDeltoids())
        } else if (muscleId == 4){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByChest())
        } else if (muscleId == 5){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByTriceps())
        } else if (muscleId == 6){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByAbd())
        } else if (muscleId == 7){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGast())
        }else if (muscleId == 8){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByGluteus())
        }else if (muscleId == 10){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByQuad())
        }else if (muscleId == 11){
            ExerciseListState(exerciseList = exercisesRepository.getExercisesFilterByFemor())
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

init {
    Log.e("SearchViewModel - hashcode - ","SearchViewModel - hashcode -"+this.hashCode())
}
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
            _workoutListState.value = ExerciseListState(showButtonAddExercise = true, exerciseId = id, exerciseList = exercisesRepository.findById(id))
            Log.d("onShowButtonAddExercise", workoutListState.value.showButtonAddExercise.toString())
            Log.d("onShowButtonAddExerciseID", id.toString())

        }
    }

    init {
        viewModelScope.launch {
            _exerciseListState.value = ExerciseListState(exerciseList = exercisesRepository.getExercises().results.map { it.toLocalModel() })
        }
    }

    data class ExerciseListState(
        val showButtonAddExercise: Boolean = false,
        val exerciseId: Int? = 0,
        val isSearching: Boolean = false,
        val exerciseList: List<Exercise> = emptyList(),
        val exercisesSelectedList: List<Exercise> = emptyList(),
        val exerciseIdList: List<Int> = emptyList(),
    )
}


