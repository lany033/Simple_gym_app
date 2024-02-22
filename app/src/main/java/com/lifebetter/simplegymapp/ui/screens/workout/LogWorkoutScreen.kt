package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout
import kotlin.time.ExperimentalTime


@Composable
fun LogWorkoutScreen(onFinish: () -> Unit) {

    val logWorkoutViewModel: LogWorkoutViewModel = hiltViewModel()

    val logState by logWorkoutViewModel.logState.collectAsState()

    val timer by logWorkoutViewModel.timer.collectAsState()

    val isPlaying = logState.timerIsPlaying

    Scaffold(topBar = { LogWorkoutBar(onFinish) }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp).padding(10.dp)) {
                Text(text = "Duration")
                BasicCountdownTimer(isPlaying = isPlaying, timerValue = timer)
                Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.padding(top = 10.dp))
            }
            LazyColumn() {

            }
        }

    }
}

@Composable
fun LogWorkoutBar(onFinish: () -> Unit) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Log Workout", fontSize = 16.sp)

            Button(onClick = { onFinish() }) {
                Text(text = "Finish")
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}

@Composable
fun LogWorkoutItem(
    nameExercise: String,
    imageUrl: String,
    id: Int,
    description: String,
    width: Int,
    height: Int,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                ImageWorkout(
                    url = imageUrl,
                    contentDescription = description,
                    width = width,
                    height = height
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            CommonTextTitle(text = nameExercise, modifier = Modifier)
        }
    }
}


@Composable
fun BasicCountdownTimer(
    isPlaying: Boolean,
    timerValue: Long,
    onStart: () -> Unit = {},
) {
    fun Long.formatTime(): String {
        val hours = this / 3600
        val minutes = (this % 3600) / 60
        val remainingSeconds = this % 60
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
    }

    if (isPlaying) {
        Text(text = timerValue.formatTime(), fontSize = 24.sp)
    }
}