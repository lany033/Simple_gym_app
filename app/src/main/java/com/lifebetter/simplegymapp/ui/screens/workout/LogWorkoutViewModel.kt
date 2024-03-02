package com.lifebetter.simplegymapp.ui.screens.workout

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.SetValue
import com.lifebetter.simplegymapp.model.database.SetWorkout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogWorkoutViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository) :
    ViewModel() {

    private val _logState = MutableStateFlow(LogWorkoutState())
    val logState = _logState.asStateFlow()

    private val _workoutId = MutableStateFlow(0)
    val workoutId = _workoutId.asStateFlow()

    //private val _kgState = MutableStateFlow("")
    //val kgState = _kgState.asStateFlow()

    //private val _repState = MutableStateFlow(0)
    //val repSet = _repState.asStateFlow()

    private val _numberSet = MutableStateFlow(mutableListOf<Int>())
    val numberSet = _numberSet.asStateFlow()

    //private val _isCheckedState = MutableStateFlow(false)
    //val isCheckedState = _isCheckedState.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _listSetWorkout = MutableStateFlow(mutableListOf<SetWorkout>())
    val listSetWorkout = _listSetWorkout.asStateFlow()

    private val _setState = MutableStateFlow(SetValueState())
    val setState = _setState.asStateFlow()

    private val _setList = MutableStateFlow(SnapshotStateList<SetValueState>())
    val setList = _setList.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            timerJob?.cancel()
            timerJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _timer.value++
                }
            }

        }
    }

    fun newSet(id: Int) {

        _setList.value.add(_setState.value)

        _setList.value.forEachIndexed{index, setValueState ->
            setValueState.setNumber = index + 1
        }

        Log.d("newset", _setList.value.joinToString { it.setNumber.toString() })

    }
    fun addSet(index:Int, set: SetValueState) {
        Log.d("index", index.toString())
        val currentList = _listSetWorkout.value
        val currentSets = currentList[index].listSet.toMutableList()
        currentSets.add(set)
        currentList[index] = currentList[index].copy(listSet = currentSets)
        _listSetWorkout.value = currentList
    }

    fun onKgTextChange(text: String) {
        _setState.update {
            SetValueState(kg = text)
        }
    }

    fun onRepTextChange(text: String) {
        _setState.update {
            SetValueState(rep = text.toInt())
        }
    }

    fun isChecked(boolean: Boolean) {

    }

    fun getWorkoutById(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _workoutId.value = id
                Log.d("id en LWVM", "$id")
            }

            exercisesRepository.findByWorkoutId(_workoutId.value)
                .collect { exercises ->
                    exercises.map {
                        _listSetWorkout.value.add(
                            SetWorkout(
                                exerciseName = it.name,
                                exerciseImage = it.images,
                                exerciseId = it.id,
                                listSet = _setList.value
                            )
                        )
                    }
                }

            Log.d("addSet", _listSetWorkout.value.joinToString { it.exerciseName })
        }
    }
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    data class LogWorkoutState(
        val timerIsPlaying: Boolean = true,
        val logExerciseList: List<Exercise> = emptyList(),
        var exerciseIndex: Int = 0,
    )
    data class SetValueState(
        var setNumber: Int = 1,
        val kg: String = "0",
        val rep: Int = 0,
        val isChecked: Boolean = false
    )

    }







