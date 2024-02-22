package com.lifebetter.simplegymapp.ui.screens.workout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.model.ExercisesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer
import kotlin.time.ExperimentalTime

@HiltViewModel
class LogWorkoutViewModel @Inject constructor(private val exercisesRepository: ExercisesRepository): ViewModel() {

    private val _logState = MutableStateFlow(LogWorkoutState())
    val logState = _logState.asStateFlow()

    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            timerJob?.cancel()
            timerJob = viewModelScope.launch {
                while (true){
                    delay(1000)
                    _timer.value++
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
        val seconds: String = "00",
        val minutes: String = "00",
        val hours: String = "00",
        val logWorkoutList: List<String> = emptyList()
    )
}