package com.lifebetter.simplegymapp.ui.screens.workout

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.database.SetWorkout
import com.lifebetter.simplegymapp.model.mappers.formatTime
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout
import kotlinx.coroutines.launch

@Composable
fun LogWorkoutScreen(onBack:() -> Unit, onFinish:() -> Unit, id: Int?) {

    val logWorkoutViewModel: LogWorkoutViewModel =
        hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val logState by logWorkoutViewModel.logState.collectAsState()
    val timer by logWorkoutViewModel.timer.collectAsState()
    val workoutId by logWorkoutViewModel.workoutId.collectAsState()

    Scaffold(topBar = { LogWorkoutBar(onFinish) }) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(text = "Duration")
                BasicCountdownTimer(isPlaying = logState.timerIsPlaying, timerValue = timer)
                Divider()
            }
            LaunchedEffect(key1 = workoutId) {
                logWorkoutViewModel.getWorkoutById(id)
            }
            LazyColumn() {
                logState.listWorkoutSet.forEachIndexed { indexWorkout, setWorkout ->
                    item {
                        LogWorkoutItem(
                            nameExercise = setWorkout.exerciseName,
                            imageUrl = setWorkout.exerciseImage,
                            id = indexWorkout,
                            description = setWorkout.exerciseName,
                            width = 30,
                            height = 30,
                        )
                    }
                    items(setWorkout.listSet) {
                        SetItem(
                            indexWorkout = indexWorkout,
                            setNumber = it.setNumber,
                            kg = it.kg,
                            rep = it.rep,
                            isChecked = it.isChecked,
                            onKg = { text ->
                                logWorkoutViewModel.onKgTextChange(
                                    text,
                                    it.setNumber - 1,
                                    indexWorkout
                                )
                            },
                            onRep = { text ->
                                logWorkoutViewModel.onRepTextChange(
                                    text,
                                    it.setNumber - 1,
                                    indexWorkout
                                )
                            },
                            onChecked = logWorkoutViewModel::isChecked
                        )
                    }
                    item {
                        AddButtonSet(
                            text = "+ Add Set",
                            id = indexWorkout,
                            onAddExercise = logWorkoutViewModel::newSet
                        )
                    }

                }
            }
        }
        BackHandler(enabled = true) {

            logWorkoutViewModel.openAlertDialog()

        }

        if (logState.openAlertDialog){
            FinishAlertDialog(
                onDismissRequest = logWorkoutViewModel::closeAlertDialog,
                onConfirmation = {
                    logWorkoutViewModel.onResetWorkout()
                    onBack() },
                dialogTitle = "Do you want discard this workout?"
            )
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

    }
}

@Composable
fun SetItem(
    indexWorkout: Int,
    setNumber: Int,
    kg: Int,
    rep: Int,
    isChecked: Boolean,
    onKg: (String) -> Unit,
    onRep: (String) -> Unit,
    onChecked: (Boolean, Int, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isChecked) {
                    Color(0xFFB8FC8C)
                } else {
                    Color.White
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = setNumber.toString(),
            onValueChange = { },
            textStyle = TextStyle.Default.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .weight(1f),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = kg.toString(),
            textStyle = TextStyle.Default.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            onValueChange = { onKg(it) },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            value = rep.toString(),
            onValueChange = { onRep(it) },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle.Default.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        IconButton(
            onClick = { onChecked(isChecked, setNumber - 1, indexWorkout) },
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .size(35.dp),
            colors = if (isChecked) {
                IconButtonDefaults.iconButtonColors(containerColor = Color.Green)
            } else {
                IconButtonDefaults.iconButtonColors(containerColor = Color.LightGray)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Check, contentDescription = "check",
                tint = Color.White,
            )
        }

    }

}

@Composable
fun BasicCountdownTimer(
    isPlaying: Boolean,
    timerValue: Long,
    onStart: () -> Unit = {},
) {

    if (isPlaying) {
        Text(text = timerValue.formatTime(), fontSize = 24.sp)
    }
}


@Composable
fun AddButtonSet(
    text: String,
    id: Int,
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


@Composable
fun FinishAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

