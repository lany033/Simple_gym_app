package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.ui.components.CommonButtonItems
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout

@Composable
fun LogWorkoutScreen(onFinish: () -> Unit, id: Int?) {

    val logWorkoutViewModel: LogWorkoutViewModel = hiltViewModel()

    val logState by logWorkoutViewModel.logState.collectAsState()

    val timer by logWorkoutViewModel.timer.collectAsState()

    val isPlaying = logState.timerIsPlaying

    val workoutId by logWorkoutViewModel.workoutId.collectAsState()

    val setNumber by logWorkoutViewModel.numberSet.collectAsState()
    val kg by logWorkoutViewModel.kgState.collectAsState()
    val rep by logWorkoutViewModel.repSet.collectAsState()
    val isChecked by logWorkoutViewModel.isCheckedState.collectAsState()
    val listSet by logWorkoutViewModel.listSetWorkout.collectAsState()

    Scaffold(topBar = { LogWorkoutBar(onFinish) }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(12.dp)
            ) {
                Text(text = "Duration")
                BasicCountdownTimer(isPlaying = isPlaying, timerValue = timer)
                Divider()
            }
            LaunchedEffect(key1 = workoutId) {
                logWorkoutViewModel.getWorkoutById(id)
            }
            LazyColumn() {
                items(listSet) {
                    LogWorkoutItem(
                        nameExercise = it.exerciseName,
                        imageUrl = it.exerciseImage,
                        setNumber = setNumber,
                        kg = kg,
                        rep = rep,
                        isChecked = isChecked,
                        description = it.exerciseName,
                        width = 30,
                        height = 30,
                        onSetNumber =  logWorkoutViewModel::onNumberTextChange ,
                        onKg = logWorkoutViewModel::onKgTextChange,
                        onRep = logWorkoutViewModel::onRepTextChange,
                        onChecked = {},
                        onAddExercise = {}
                    )
                }
            }
        }

    }
}

@Composable
fun LogWorkoutBar(onFinish: () -> Unit) {
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = "Log Workout",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Button(onClick = { onFinish() }, modifier = Modifier.align(Alignment.CenterEnd)) {
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
    setNumber: Int,
    kg: String,
    rep: Int,
    isChecked: Boolean,
    description: String,
    width: Int,
    height: Int,
    onSetNumber: (String) -> Unit,
    onKg: (String) -> Unit,
    onRep: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
    onAddExercise: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
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
        Spacer(modifier = Modifier.size(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "SET", textAlign = TextAlign.Center ,modifier = Modifier.weight(1f).padding(5.dp))
            Text(text = "KG", textAlign = TextAlign.Center,modifier = Modifier.weight(1f).padding(5.dp))
            Text(text = "REPS", textAlign = TextAlign.Center,modifier = Modifier.weight(1f).padding(5.dp))
            Icon(imageVector = Icons.Filled.Check, contentDescription = "check", modifier = Modifier.weight(1f).padding(5.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(value = setNumber.toString(), onValueChange = {onSetNumber(String())}, modifier = Modifier.weight(1f).padding(5.dp))
            TextField(value = kg, onValueChange = {onKg(String())}, modifier = Modifier.weight(1f).padding(5.dp))
            TextField(value = rep.toString(), onValueChange = {onRep(String())}, modifier = Modifier.weight(1f).padding(5.dp))
            IconButton(onClick = {onChecked(isChecked)}, modifier = Modifier.weight(1f).padding(5.dp)) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "check")
            }

        }
        Spacer(modifier = Modifier.size(5.dp))
        CommonButtonItems(text = "+ Add Set") { onAddExercise() }
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
