package com.lifebetter.simplegymapp.ui.screens.workout

import android.graphics.Bitmap
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
import kotlinx.coroutines.flow.updateAndGet
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

    fun getWorkoutById(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                _workoutId.value = id
                Log.d("id en LWVM", "$id")
            }
            val exercisesList = exercisesRepository.findByWorkoutId(_workoutId.value)

            exercisesList.collect { exercises ->
                _logState.update {
                    it.copy(listWorkoutSet = exercises.map {
                        SetWorkout(
                            exerciseName = it.name,
                            exerciseImage = it.images,
                            exerciseId = it.id,
                            listSet = _logState.value.listSetValueState,
                            listImage = null,
                            dateTime = null,
                            timer = 0L
                        )
                    })
                }
            }
        }
    }

    fun newSet(indexSet: Int) {

        val currentList = _logState.value.listWorkoutSet
        val currentSets = currentList[indexSet].listSet

        val newSets = currentSets.plus(_setState.value).mapIndexed { index, setValueState ->
            SetValueState(
                setNumber = index + 1,
                kg = setValueState.kg,
                rep = setValueState.rep,
                isChecked = false
            )
        }

        val updatedList = currentList.toMutableList()
        updatedList[indexSet] = updatedList[indexSet].copy(listSet = newSets)

        _logState.update {
            it.copy(listWorkoutSet = updatedList)
        }

        Log.d("size W $indexSet", _logState.value.listWorkoutSet[indexSet].listSet.size.toString())
    }

    fun onKgTextChange(text: String, index: Int, indexWorkout: Int) {
        val listWorkout = _logState.value.listWorkoutSet.mapIndexed { i, setWorkout ->
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
        }

        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }
    }

    fun onSumKg() {
        var sumKg = 0
        _listSetWorkout.value.forEach {
            it.listSet.forEach {
                sumKg += it.kg
            }
        }

        _logState.update {
            it.copy(sumKg = sumKg)
        }
    }

    fun onRepTextChange(text: String, index: Int, indexWorkout: Int) {
        val listWorkout = _logState.value.listWorkoutSet.mapIndexed { i, setWorkout ->
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
        }

        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }
    }

    fun isChecked(boolean: Boolean, index: Int, indexWorkout: Int) {
        val listWorkout = _logState.value.listWorkoutSet.mapIndexed { i, setWorkout ->
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
        }
        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }
    }

    fun closeAlertDialog() {
        _logState.update {
            it.copy(openAlertDialog = false)
        }
    }

    fun openAlertDialog() {
        _logState.update {
            it.copy(openAlertDialog = true)
        }
    }


    fun finishWorkout(list: List<SetWorkout>) {
        viewModelScope.launch {
            exercisesRepository.saveNewSetWorkout(list)
        }
    }

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _permission = MutableStateFlow(false)
    val permission = _permission.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

    fun permisseIsGranted() {
        _permission.update {
            true
        }
    }

    data class LogWorkoutState(
        val timerIsPlaying: Boolean = true,
        var timerJob: Job? = null,
        val listSetValueState: List<SetValueState> = emptyList(),
        val listWorkoutSet: List<SetWorkout> = emptyList(),
        val sumKg: Int = 0,
        val openAlertDialog: Boolean = false
    )

    data class SetValueState(
        val setNumber: Int = 1,
        val kg: Int = 0,
        val rep: Int = 0,
        val isChecked: Boolean = false
    )

}







