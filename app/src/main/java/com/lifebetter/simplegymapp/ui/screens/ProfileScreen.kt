package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
fun ProfileScreen(
    onClickMeasures: () -> Unit,
    onClickStatistics: () -> Unit,
    onClickCalendar: () -> Unit
) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by profileViewModel.profileState.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "lany033") }) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {

            item {
                Header(profileState.workoutCount)
                Dashboard(
                    onClickMeasures = onClickMeasures,
                    onClickCalendar = onClickCalendar,
                    onClickStatistics = onClickStatistics
                )
            }

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
                items(workoutSession.setWorkout) { setWorkout ->
                    WorkoutExerciseList(
                        url = setWorkout.exerciseImage,
                        description = setWorkout.exerciseName,
                        nameExercise = setWorkout.exerciseName
                    )
                }
                item {
                    Spacer(modifier = Modifier.size(14.dp))
                }
            }
        }

    }
}

@Composable
fun Header(workoutCount: Int) {
    Card(shape = RectangleShape, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(14.dp)) {
            CommonCirclePhoto(R.drawable.all, 80)
            Spacer(modifier = Modifier.size(14.dp))
            Column {
                CommonTextTitle(
                    text = "Melanie Mantilla",
                    modifier = Modifier
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

}

@Composable
fun Dashboard(
    onClickMeasures: () -> Unit,
    onClickStatistics: () -> Unit,
    onClickCalendar: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
        Text(text = "Dashboard", modifier = Modifier.padding(start = 12.dp, end = 12.dp))
        Row(Modifier.padding(start = 12.dp, end = 12.dp)) {
            CommonButtonProfileDashboard(
                "Statistics",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.ChartBar,
                onclick = { onClickStatistics() })
            Spacer(modifier = Modifier.padding(2.dp))
            CommonButtonProfileDashboard(
                "Exercises",
                Modifier.weight(1f),
                imageVector = FontAwesomeIcons.Solid.Dumbbell,
                onclick = { }
            )
        }
        Row(Modifier.padding(start = 12.dp, end = 12.dp)) {
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
                onclick = { onClickCalendar() })
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
        border = BorderStroke(1.5.dp, Color.LightGray),
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



