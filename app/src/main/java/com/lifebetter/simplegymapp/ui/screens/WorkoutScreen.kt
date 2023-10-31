package com.lifebetter.simplegymapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.model.getWorkouts
import com.lifebetter.simplegymapp.ui.components.CardWorkout
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar


@Composable
fun WorkoutScreen( ) {
    Scaffold(topBar = { MyTopAppBar(title = "Workout")}) {padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(getWorkouts()) { workout ->
                    CardWorkout(workout.name)
                }
            }
        }
    }

}





