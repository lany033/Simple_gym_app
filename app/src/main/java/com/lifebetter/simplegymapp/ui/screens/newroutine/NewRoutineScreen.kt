package com.lifebetter.simplegymapp.ui.screens.newroutine

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithTwoText
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout
import com.lifebetter.simplegymapp.ui.screens.exercises.ExerciseViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell

@Composable
fun NewRoutineScreen(onClickAddExercises: () -> Unit, onCancel:()->Unit) {
    val exerciseViewModel: ExerciseViewModel =
        hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val selectedExercises by exerciseViewModel.selectedExercises.collectAsState()
    val titleText by exerciseViewModel.nameWorkout.collectAsState()
    val openAlertDialog by exerciseViewModel.openAlertDialog.collectAsState()

    Scaffold(topBar = {
        MyTopBarWithTwoText(
            "Cancel",
            "Build Routine",
            "Save",
            {onCancel()},
            exerciseViewModel::openAlertDialog)
    }) { padding ->
        if (openAlertDialog){
            SaveAlertDialog(
                nameTitle = titleText,
                listWorkout = selectedExercises,
                onDismissRequest = exerciseViewModel::closeAlertDialog ,
                onConfirmation = exerciseViewModel::onSaveRoutine ,
                dialogTitle = "¿Guardar Rutina?",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                value = titleText,
                onValueChange = exerciseViewModel::onNameText,
                placeholder = {
                    Text(
                        text = "Titulo de la rutina",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Gray
                    )
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
            Box {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    if (selectedExercises.isEmpty()) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Dumbbell,
                            contentDescription = "pesa",
                            modifier = Modifier.size(26.dp)
                        )
                        Text(
                            text = "Empieza agregando un ejercicio a tu rutina",
                            color = Color.LightGray
                        )
                    } else {
                        LazyColumn() {
                            items(selectedExercises) { exercise ->
                                Log.d("exercise Id lazy", exercise.name)
                                WorkoutItem(
                                    nameExercise = exercise.name,
                                    imageUrl = exercise.images,
                                    width = 30,
                                    height = 30
                                ) { exerciseViewModel.deleteElement(exercise) }
                            }
                        }
                    }
                    CommonButtonItems2(text = "+ Agregar Ejercicio", onClick = {
                        exerciseViewModel::offShowButtonExercise.invoke()
                        onClickAddExercises()
                    })
                }
            }
        }
    }
}

@Composable
fun WorkoutItem(
    nameExercise: String,
    imageUrl: String,
    width: Int,
    height: Int,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    ImageWorkout(
                        url = imageUrl,
                        contentDescription = nameExercise,
                        width = width,
                        height = height
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = nameExercise)
                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
                }
            }
        }
    }
}


@Composable
fun CommonButtonItems2(text: String, onClick: () -> Unit) {
    Button(onClick = {
        onClick()
    }, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
        CommonTextButtons(text = text)
    }
}


@Composable
fun SaveAlertDialog(
    nameTitle: String,
    listWorkout: List<Exercise>,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, List<Exercise>) -> Unit,
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
                    onConfirmation(nameTitle,listWorkout)
                }
            ) {
                Text("Save")
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
