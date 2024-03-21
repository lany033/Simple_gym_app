package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonLittleText
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar
import com.lifebetter.simplegymapp.ui.theme.Green40
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.Dumbbell
import compose.icons.fontawesomeicons.solid.Ruler

@Composable
fun ProfileScreen(onClickExercises: () -> Unit, onClickMeasures: () -> Unit) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by profileViewModel.profileState.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "lany033") }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Header(profileState.workoutCount)
            Dashboard(onClickExercises = onClickExercises, onClickMeasures = onClickMeasures)
            LazyColumn() {
                profileState.listWorkoutSession.forEach { workoutSession ->
                    item {
                        WorkoutSessionCard(
                            workoutSession = workoutSession,
                            nameWorkout = workoutSession.nameWorkout,
                            time = workoutSession.timer,
                            volumeTotal = workoutSession.sumKg,
                            date = workoutSession.createdDateFormatted,
                            icon = Icons.Filled.Delete,
                            onDelete = profileViewModel::deleteWorkout
                        )
                    }
                    items(workoutSession.setWorkout){ setWorkout ->
                        WorkoutExerciseList(
                            url = setWorkout.exerciseImage,
                            description = setWorkout.exerciseName,
                            nameExercise = setWorkout.exerciseName
                        )
                    }
                    item { 
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Header(workoutCount: Int) {
    Row {
        CommonCirclePhoto(R.drawable.perfilphoto, 70)
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            CommonTextTitle(
                text = "Melanie Mantilla",
                modifier = Modifier.padding(1.dp)
            )
            Box {
                Column {
                    CommonLittleText(text = "Workouts")
                    Text(text = workoutCount.toString())
                }
            }
        }
    }
}

@Composable
fun Dashboard(onClickExercises: () -> Unit, onClickMeasures: () -> Unit) {
    Column {
        Text(text = "Dashboard")
        Row {
            CommonButtonProfileDashboard(
                "Statistics",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.ChartBar,
                onclick = { })
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard(
                "Exercises",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Dumbbell,
                onclick = { onClickExercises() }
            )
        }
        Row {
            CommonButtonProfileDashboard(
                "Measures",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Ruler,
                onclick = { onClickMeasures() })
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard(
                "Calendar",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Calendar,
                onclick = {})
        }
    }
}

@Composable
fun CommonButtonProfileDashboard(
    textButton: String,
    modifier: Modifier,
    imageVector: ImageVector,
    onclick: () -> Unit
) {
    Button(
        onClick = { onclick() },
        shape = RoundedCornerShape(4.dp), modifier = modifier,
        border = BorderStroke(2.dp, Color.LightGray),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "icon",
            modifier = Modifier.size(24.dp),
            tint = Green40
        )
        CommonTextButtons(text = textButton)
    }
}



