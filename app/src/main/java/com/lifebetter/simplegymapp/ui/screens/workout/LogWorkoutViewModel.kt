package com.lifebetter.simplegymapp.ui.screens.workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.model.database.SetWorkout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _listSetWorkout = MutableStateFlow(mutableListOf<SetWorkout>())
    val listSetWorkout = _listSetWorkout.asStateFlow()

    private val _setState = MutableStateFlow(SetValueState())


    init {
        viewModelScope.launch {
            logState.value.timerJob?.cancel()
            logState.value.timerJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _timer.value++
                }
            }

        }
    }

    fun newSet(indexSet: Int) {

        val currentList = _listSetWorkout.value
        val currentSets = currentList[indexSet].listSet.toMutableList()

        currentSets.add(_setState.value)

        val newSets = currentSets.mapIndexed { index, setValueState ->
            SetValueState(
                setNumber = index + 1,
                kg = setValueState.kg,
                rep = setValueState.rep,
                isChecked = false
            )
        }

        currentList[indexSet] = currentList[indexSet].copy(listSet = newSets)

        Log.d("size $indexSet", _listSetWorkout.value[indexSet].listSet.size.toString())

    }

    fun onKgTextChange(text: String, index: Int, indexWorkout: Int) {
        _listSetWorkout.update { list ->
            list.mapIndexed { i, setWorkout ->
                if (i == indexWorkout) {
                    setWorkout.copy(listSet = setWorkout.listSet.mapIndexed { indexList, setValueState ->
                        if (indexList == index) {
                            setValueState.copy(kg = text.toInt())
                        } else {
                            setValueState
                        }
                    })
                } else {
                    setWorkout
                }
            }.toMutableList()
        }
    }

    fun onRepTextChange(text: String, index: Int, indexWorkout: Int) {
        _listSetWorkout.update { list ->
            list.mapIndexed { i, setWorkout ->
                if (i == indexWorkout) {
                    setWorkout.copy(listSet = setWorkout.listSet.mapIndexed { indexList, setValueState ->
                        if (indexList == index) {
                            setValueState.copy(rep = text.toInt())
                        } else {
                            setValueState
                        }
                    })
                } else {
                    setWorkout
                }
            }.toMutableList()
        }
    }

    fun isChecked(boolean: Boolean, index: Int, indexWorkout: Int) {
        _listSetWorkout.update { list ->
            list.mapIndexed { i, setWorkout ->
                if (i == indexWorkout) {
                    setWorkout.copy(listSet = setWorkout.listSet.mapIndexed { indexList, setValueState ->
                        if (indexList == index) {
                            setValueState.copy(isChecked = !boolean)
                        } else {
                            setValueState
                        }
                    })
                } else {
                    setWorkout
                }
            }.toMutableList()
        }
    }

    fun getWorkoutById(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _workoutId.value = id
                Log.d("id en LWVM", "$id")
            }

            val exercisesList = exercisesRepository.findByWorkoutId(_workoutId.value)

            exercisesList.collect { exercises ->

                _listSetWorkout.value = exercises.map {
                    SetWorkout(
                        exerciseName = it.name,
                        exerciseImage = it.images,
                        exerciseId = it.id,
                        listSet = _logState.value.listSetValueState
                    )
                }.toMutableList()
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        logState.value.timerJob?.cancel()
    }

    data class LogWorkoutState(
        val timerIsPlaying: Boolean = true,
        var timerJob: Job? = null,
        val listSetValueState: List<SetValueState> = emptyList()
    )

    data class SetValueState(
        val setNumber: Int = 1,
        val kg: Int = 0,
        val rep: Int = 0,
        val isChecked: Boolean = false
    )

}







