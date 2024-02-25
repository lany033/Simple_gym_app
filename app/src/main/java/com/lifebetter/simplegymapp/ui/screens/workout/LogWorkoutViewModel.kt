package com.lifebetter.simplegymapp.ui.screens.workout

import android.util.Log
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

    private val _kgState = MutableStateFlow("")
    val kgState = _kgState.asStateFlow()

    private val _repState = MutableStateFlow(0)
    val repSet = _repState.asStateFlow()

    private val _numberSet = MutableStateFlow(1)
    val numberSet = _numberSet.asStateFlow()

    private val _isCheckedState = MutableStateFlow(false)
    val isCheckedState = _isCheckedState.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private val _listSetWorkout = MutableStateFlow(mutableListOf<SetWorkout>())
    val listSetWorkout = _listSetWorkout.asStateFlow()

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

    fun onNumberTextChange(text: String) {
        _numberSet.value = text.toInt()
    }

    fun onKgTextChange(text: String) {
        _kgState.value = text
    }

    fun onRepTextChange(text: String) {
        _repState.value = text.toInt()
    }

    fun isChecked(boolean: Boolean) {
        _isCheckedState.value = boolean
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
                                setNumber = _numberSet.value,
                                rep = _repState.value,
                                kg = kgState.value,
                                isChecked = false
                            )
                        )
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    data class LogWorkoutState(
        val timerIsPlaying: Boolean = true,
        val logWorkoutList: List<Exercise> = emptyList()

    )
}