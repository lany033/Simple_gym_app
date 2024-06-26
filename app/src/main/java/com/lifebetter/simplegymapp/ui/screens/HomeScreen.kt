package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.domain.SetWorkout
import com.lifebetter.simplegymapp.domain.WorkoutSession
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.ImageWorkout
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar
import com.lifebetter.simplegymapp.ui.theme.Green40
import com.lifebetter.simplegymapp.ui.theme.Green80

@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val homeState by homeViewModel.homeState.collectAsState()

    Scaffold(topBar = { MyTopAppBar(title = "Home") }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            if (homeState.listWorkoutSession.isEmpty()) {
                WelcomeHome()
            }
            LazyColumn() {
                homeState.listWorkoutSession.forEach { workoutSession ->
                    item {
                        WorkoutSessionCard(
                            workoutSession = workoutSession,
                            nameWorkout = workoutSession.nameWorkout,
                            time = workoutSession.timer,
                            volumeTotal = workoutSession.sumKg,
                            date = workoutSession.createdDateFormatted,
                            icon = null,
                            onDelete = {}
                        )
                    }
                    item {
                        HorizontalPagerWithIndicators(
                            uris = workoutSession.uri,
                            setWorkout = workoutSession.setWorkout
                        )
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithIndicators(uris: List<String>?, setWorkout: List<com.lifebetter.simplegymapp.domain.SetWorkout>) {

    val pagerCount = if (uris.isNullOrEmpty()) {
        1
    } else {
        uris.size
    }

    val pagerState = rememberPagerState(pageCount = { pagerCount })

    Card(
        modifier = Modifier
            .padding(bottom = 15.dp),
        shape = RectangleShape
    ) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
            modifier = Modifier
                .height(350.dp)
        ) {
            if (uris.isNullOrEmpty()) {
                Box(contentAlignment = Alignment.TopStart) {
                    WorkoutList(setWorkout = setWorkout)
                }
            } else {
                ImageHomeWorkout(uri = uris[it], description = "")
            }
        }

        Row(
            Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Green40 else Green80
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color, CircleShape)
                        .size(10.dp)
                )
            }
        }
    }
}


@Composable
fun WorkoutSessionCard(
    nameWorkout: String,
    workoutSession: com.lifebetter.simplegymapp.domain.WorkoutSession,
    time: Long,
    volumeTotal: Int,
    date: String,
    icon: ImageVector?,
    onDelete: (com.lifebetter.simplegymapp.domain.WorkoutSession) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.align(Alignment.CenterStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CommonCirclePhoto(R.drawable.all, 65)
                    Spacer(modifier = Modifier.size(14.dp))
                    Column {
                        CommonTextTitle(
                            text = "Melanie Mantilla",
                            modifier = Modifier
                        )
                        Text(text = date)
                    }
                }

                if (icon != null) {
                    IconButton(
                        modifier = Modifier.align(Alignment.TopEnd),
                        onClick = { onDelete(workoutSession) }) {
                        Icon(
                            tint = Green40,
                            imageVector = icon,
                            contentDescription = null
                        )
                    }

                }
            }
            CommonTextTitle(text = nameWorkout, modifier = Modifier)
            Row {
                Duration(modifier = Modifier.weight(1f), time)
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(text = "Volume")
                    Text(text = volumeTotal.toString(), fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun WorkoutList(
    setWorkout: List<SetWorkout>
) {
    LazyColumn() {
        items(setWorkout) { setWorkout ->
            WorkoutExerciseList(
                url = setWorkout.exerciseImage,
                description = setWorkout.exerciseName,
                nameExercise = setWorkout.exerciseName
            )
        }
    }
}

@Composable
fun ImageHomeWorkout(
    uri: String,
    description: String
) {
    val painter: Painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painter, contentDescription = description, contentScale = ContentScale.Crop)
    }
}

@Composable
fun WorkoutExerciseList(url: String, description: String, nameExercise: String) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
        Row(modifier = Modifier.padding(14.dp)) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                ImageWorkout(
                    url = url,
                    contentDescription = description,
                    width = 25,
                    height = 25
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            CommonTextTitle(text = nameExercise, modifier = Modifier)
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
            imageVector = Icons.Filled.SportsGymnastics,
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



