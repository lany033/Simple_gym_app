package com.lifebetter.simplegymapp.ui.screens.newroutine

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.ui.components.CommonButtonItems
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithTwoText
import com.lifebetter.simplegymapp.ui.screens.WorkoutViewModel
import com.lifebetter.simplegymapp.ui.screens.exercises.ImageWorkout
import com.lifebetter.simplegymapp.ui.screens.exercises.SearchViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell


@Composable
fun NewRoutineScreen(onClickAddExercises: (Int?) -> Unit, exerciseId: Int?) {

    //val workoutViewModel: WorkoutViewModel = viewModel { WorkoutViewModel(98()) }

    val searchViewModel: SearchViewModel = viewModel{

        SearchViewModel(ExercisesRepository())
    }
    //val exerciseList by searchViewModel.exerciseListState.collectAsState()
    val workoutListState by searchViewModel.workoutListState.collectAsState()

    //val exerciseList by workoutViewModel.workoutListState.collectAsState()

    Scaffold(topBar = { MyTopBarWithTwoText("Cancel", "Build Routine", "Save") }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                value = "Titulo de la rutina",
                onValueChange = {},
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
                    if (workoutListState.exerciseId == 0){
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
                            Log.d("lazycolumn", workoutListState.exerciseList.joinToString { it.name })
                            items(workoutListState.exerciseList) {
                                Log.d("exercise Id lazy", it.name)
                                Text(text = it.name)
                            }
                        }
                    }


                    CommonButtonItems2(text = "+ Agregar Ejercicio", exerciseId = exerciseId ,onClick = {
                        onClickAddExercises(exerciseId)

                    })

                    exerciseId?.let {
                        Text(text = exerciseId.toString())
                        Text(text = workoutListState.exerciseId.toString())
                    }
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
    height: Int
) {
    Card {
        Column {
            Row {
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
            }
        }
    }
}


@Composable
fun CommonButtonItems2(text: String, exerciseId: Int?,onClick: (Int?) -> Unit) {
    Button(onClick = {
        onClick(exerciseId)
    }, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
        CommonTextButtons(text = text)
    }
}
