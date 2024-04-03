package com.lifebetter.simplegymapp.ui.screens.workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.framework.toSetValueDomain
import com.lifebetter.simplegymapp.framework.toWorkoutSessionDomain
import com.lifebetter.simplegymapp.framework.database.WorkoutSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogWorkoutViewModel @Inject constructor(
    private val exercisesRepository: ExercisesRepository
) :
    ViewModel() {

    private val _logState = MutableStateFlow(LogWorkoutState())
    val logState = _logState.asStateFlow()

    private val _workoutId = MutableStateFlow(0)
    val workoutId = _workoutId.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _uri = MutableStateFlow<List<String>>(emptyList())
    val uri = _uri.asStateFlow()

    private val _setState = MutableStateFlow(SetValueState())

    init {
        Log.e("LogWVM - hashcode - ", "LogWVM - hashcode -" + this.hashCode())
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
        _logState.value = _logState.value.copy(timerIsPlaying = true)
        viewModelScope.launch {
            if (id != null) {
                _workoutId.value = id
                Log.d("id en LWVM", "$id")
            }
            val exercisesList = exercisesRepository.findByWorkoutId(_workoutId.value)

            exercisesList.collect { exercises ->
                try {
                    _logState.update {
                        it.copy(listWorkoutSet = exercises.exerciseList.map {
                            com.lifebetter.simplegymapp.domain.SetWorkout(
                                exerciseName = it.name,
                                exerciseImage = it.images,
                                exerciseId = it.id,
                                listSet = _logState.value.listSetValueState.map { it.toSetValueDomain() }
                            )
                        }, nameWorkout = exercises.nameWorkout)
                    }
                } catch (e: Exception){
                    println(e)
                }

            }
        }
    }

    fun newSet(indexSet: Int) {

        val currentList = _logState.value.listWorkoutSet
        val currentSets = currentList[indexSet].listSet

        val newSets = currentSets.plus(_setState.value.toSetValueDomain()).mapIndexed { index, setValueState ->
            SetValueState(
                setNumber = index + 1,
                kg = setValueState.kg,
                rep = setValueState.rep,
                isChecked = false
            )
        }

        val updatedList = currentList.toMutableList()
        updatedList[indexSet] = updatedList[indexSet].copy(listSet = newSets.map { it.toSetValueDomain() })

        _logState.value = _logState.value.copy(listWorkoutSet = updatedList)

        Log.d("size W $indexSet", _logState.value.listWorkoutSet[indexSet].listSet.size.toString())
    }

    fun onKgTextChange(text: String, index: Int, indexWorkout: Int) {
        val listWorkout = _logState.value.listWorkoutSet.mapIndexed { i, setWorkout ->
            if (i == indexWorkout) {
                setWorkout.copy(listSet = setWorkout.listSet.mapIndexed { indexList, setValueState ->
                    try {
                        if (indexList == index) {
                            setValueState.copy(kg = text.toInt())
                        } else {
                            setValueState
                        }
                    } catch (e: Exception) {
                        println(e)
                        setValueState
                    }
                }.toMutableList())
            } else {
                setWorkout
            }
        }
        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }
        onSumKg()
    }

    fun onSumKg() {
        var sumKg = 0
        _logState.value.listWorkoutSet.forEach {
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
                    try {
                        if (indexList == index) {
                            setValueState.copy(rep = text.toInt())
                        } else {
                            setValueState
                        }
                    } catch (e: Exception){
                        println(e)
                        setValueState
                    }

                }.toMutableList())
            } else {
                setWorkout
            }
        }

        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }

        onSumRep()
    }

    fun onSumRep() {
        var sumRep = 0
        _logState.value.listWorkoutSet.forEach {
            it.listSet.forEach {
                sumRep += it.rep
            }
        }

        _logState.update {
            it.copy(sumRep = sumRep)
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
                }.toMutableList())
            } else {
                setWorkout
            }
        }
        _logState.update {
            it.copy(listWorkoutSet = listWorkout)
        }

    }

    fun saveWorkoutSession() {
        val newWorkout = WorkoutSession(
            setWorkout = _logState.value.listWorkoutSet,
            sumKg = _logState.value.sumKg,
            sumRep = _logState.value.sumRep,
            uri = _uri.value,
            timer = _timer.value,
            nameWorkout = _logState.value.nameWorkout
        )
        viewModelScope.launch {
            exercisesRepository.saveWorkoutSession(newWorkout.toWorkoutSessionDomain())
        }
    }

    fun onTakePhoto(uri: String) {
        _uri.value += uri
    }

    fun permissionIsGranted() {
        _logState.update {
            it.copy(permission = true)
        }
    }

    fun openAlertDialog() {
        _logState.update {
            it.copy(openAlertDialog = true)
        }
    }

    fun closeAlertDialog() {
        _logState.update {
            it.copy(openAlertDialog = false)
        }
    }

    fun onResetWorkout() {
        _timer.value = 0L
        _logState.value = _logState.value.copy(openAlertDialog = false,
            sumKg = 0,
            sumRep = 0,
            timerIsPlaying = false)

    }

    data class LogWorkoutState(
        val nameWorkout: String = "",
        val timerIsPlaying: Boolean = true,
        var timerJob: Job? = null,
        val listSetValueState: MutableList<SetValueState> = mutableListOf(),
        val listWorkoutSet: List<com.lifebetter.simplegymapp.domain.SetWorkout> = emptyList(),
        val sumKg: Int = 0,
        val sumRep: Int = 0,
        val openAlertDialog: Boolean = false,
        val permission: Boolean = false,
        val isBack: Boolean = true,
    )

    data class SetValueState(
        val setNumber: Int = 1,
        val kg: Int = 0,
        val rep: Int = 0,
        val isChecked: Boolean = false
    )

}












