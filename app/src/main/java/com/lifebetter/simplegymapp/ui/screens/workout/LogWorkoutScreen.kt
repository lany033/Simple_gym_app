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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lifebetter.simplegymapp.model.database.SetValue
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout

@Composable
fun LogWorkoutScreen(onFinish: () -> Unit, id: Int?) {

    val logWorkoutViewModel: LogWorkoutViewModel = hiltViewModel()

    val logState by logWorkoutViewModel.logState.collectAsState()

    val timer by logWorkoutViewModel.timer.collectAsState()

    val isPlaying = logState.timerIsPlaying

    val workoutId by logWorkoutViewModel.workoutId.collectAsState()

    //val setNumber by logWorkoutViewModel.numberSet.collectAsState()
    /*
    val kg by logWorkoutViewModel.kgState.collectAsState()
    val rep by logWorkoutViewModel.repSet.collectAsState()
    val isChecked by logWorkoutViewModel.isCheckedState.collectAsState()
    */
    val workoutList by logWorkoutViewModel.listSetWorkout.collectAsState()
    val setList by logWorkoutViewModel.setList.collectAsState()
    val setState by logWorkoutViewModel.setState.collectAsState()

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
                workoutList.forEachIndexed { index, setWorkout ->
                    item {
                        LogWorkoutItem(
                            nameExercise = setWorkout.exerciseName,
                            imageUrl = setWorkout.exerciseImage,
                            id = index,
                            description = setWorkout.exerciseName,
                            width = 30,
                            height = 30,
                        )
                    }
                    items(setWorkout.listSet) {
                        SetItem(
                            id = index,
                            setNumber = it.setNumber,
                            kg = it.kg,
                            rep = setState.rep,
                            isChecked = false,
                            viewModel = logWorkoutViewModel,
                            onKg = { text -> logWorkoutViewModel.onKgTextChange(text,it.setNumber - 1) },
                            onRep = { },
                            onChecked = { }
                        )
                    }
                    item{
                        AddButtonSet(
                            text = "+ Add Set",
                            id = index,
                            setValue = setState,
                            onAddExercise = logWorkoutViewModel::newSet
                        )
                    }

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
    id: Int,
    description: String,
    width: Int,
    height: Int,

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
            Text(
                text = "SET", textAlign = TextAlign.Center, modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
            Text(
                text = "KG", textAlign = TextAlign.Center, modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
            Text(
                text = "REPS", textAlign = TextAlign.Center, modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
            Icon(
                imageVector = Icons.Filled.Check, contentDescription = "check", modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
        }
        Spacer(modifier = Modifier.size(5.dp))

    }
}

@Composable
fun SetItem(
    id: Int,
    setNumber: Int,
    kg: String,
    rep: Int,
    isChecked: Boolean,
    viewModel: LogWorkoutViewModel,
    onKg:(String) -> Unit,
    onRep:() -> Unit,
    onChecked:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 3.dp, bottom = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = setNumber.toString(),
            onValueChange = { },
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
        TextField(
            value = kg,
            onValueChange = { onKg(it) },
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            placeholder = {
                Text(
                    text = "000"
                )
            })
        TextField(
            value = rep.toString(),
            onValueChange = { },
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        )
        IconButton(
            onClick = {  }, modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "check")
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


@Composable
fun AddButtonSet(
    text: String,
    id: Int,
    setValue: LogWorkoutViewModel.SetValueState,
    onAddExercise: (Int) -> Unit
) {
    Button(
        onClick = { onAddExercise(id) },
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        CommonTextButtons(text = text)
    }
}