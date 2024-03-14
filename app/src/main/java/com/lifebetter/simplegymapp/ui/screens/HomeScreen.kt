package com.lifebetter.simplegymapp.ui.screens

import android.graphics.Bitmap
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.model.database.SetWorkout
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar
import java.time.LocalDateTime

@Composable
fun HomeScreen() {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeState by homeViewModel.homeState.collectAsState()

    Scaffold(topBar = { MyTopAppBar(title = "Home") }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(12.dp)) {

            if (homeState.listWorkoutSession.isEmpty()) {
                WelcomeHome()
            }
            LazyColumn {
                items(homeState.listWorkoutSession) { workoutSession ->
                    WorkoutSessionItem(
                        nameWorkout = workoutSession.nameWorkout,
                        time = workoutSession.timer,
                        volumenTotal = workoutSession.sumKg,
                        list = workoutSession.setWorkout,
                        date = workoutSession.date,
                        bitmap = workoutSession.bitmap
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }

            }
        }
    }
}

@Composable
fun WorkoutSessionItem(
    nameWorkout: String,
    time: Long?,
    volumenTotal: Int,
    list: List<SetWorkout>,
    date: LocalDateTime?,
    bitmap: List<Bitmap>?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            CommonCirclePhoto(R.drawable.perfilphoto, 70)
            Spacer(modifier = Modifier.size(12.dp))
            Column {
                CommonTextTitle(
                    text = "Melanie Mantilla",
                    modifier = Modifier
                )
                Text(text = "date.toString()")
            }
        }
        CommonTextTitle(text = nameWorkout, modifier = Modifier)
        Row {
            Duration(modifier = Modifier.weight(1f), time)
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Volume")
                Text(text = volumenTotal.toString(), fontSize = 20.sp)

            }
        }
        Divider()
        Text(text = "Workout")
        Row {

        }
    }
}

@Composable
fun WelcomeHome() {
    Column(
        modifier = Modifier.padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Start a new routine",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            modifier = Modifier.size(70.dp),
            imageVector = Icons.Filled.AccessibilityNew,
            contentDescription = "newRoutine"
        )
        Spacer(modifier = Modifier.size(60.dp))
        Card(
            shape = RectangleShape,
            modifier = Modifier.padding(20.dp),
            colors = CardDefaults.cardColors(
                Color.Transparent
            )
        ) {
            Row {
                Card(
                    modifier = Modifier.size(50.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(Color.LightGray)
                ) {}
                Spacer(modifier = Modifier.padding(8.dp))
                Card(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(Color.LightGray)
                ) {}
            }
            Spacer(modifier = Modifier.size(8.dp))
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.LightGray)
            ) {}
        }
    }
}



