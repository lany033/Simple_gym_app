package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.ui.components.CommonDivider
import com.lifebetter.simplegymapp.ui.components.CommonMediumText
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar

@Composable
fun ExercisesScreen() {

    var showButton by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel: ExercisesViewModel = viewModel {
        ExercisesViewModel(ExercisesRepository())
    }

    val state by viewModel.state.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "Exercises") }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp),
                shape = RoundedCornerShape(4.dp),
                textStyle = TextStyle(fontSize = 16.sp),
                singleLine = true,
                value = " ",
                onValueChange = {},
                placeholder = { Text(text = "Search exercise", fontSize = 16.sp) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search"
                    )
                })
            Row() {
                Button(
                    onClick = { /*TODO*/ }, shape = RoundedCornerShape(4.dp), modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All equipment", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.size(15.dp))
                Button(
                    onClick = { /*TODO*/ }, shape = RoundedCornerShape(4.dp), modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All Muscles", fontSize = 16.sp)
                }
            }
            Text(text = "Ejercicios Populares")

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(state.exercises){
                        ExerciseItem(
                            nameExercise = it.name,
                            muscle = it.muscles,
                            imageUrl = it.images,
                            description = it.name,
                            width = 50,
                            height = 50,
                            onExerciseClick = { showButton = true }
                        )
                        CommonDivider()
                    }

                }
                if (showButton) {
                    Button(
                        onClick = { showButton = false },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .animateContentSize()
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(text = "Agrega 1 ejercicio")
                    }
                }
            }

        }
    }
}

@Composable
fun ExerciseItem(nameExercise: String, muscle: String, imageUrl: String, description: String, width: Int, height: Int, onExerciseClick:()->Unit){
    Card(modifier = Modifier
        .clickable(onClick = onExerciseClick)
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 10.dp), colors = CardDefaults.cardColors(containerColor = Color.Transparent)) {
        Row {
            Card(shape = CircleShape, colors = CardDefaults.cardColors(containerColor = Color.White)) {
                ImageWorkout(url = imageUrl, contentDescription = description, width = width, height = height)
            }
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                CommonTextTitle(text = nameExercise, modifier = Modifier)
                CommonMediumText(text = muscle)
            }
        }
    }
    Log.d("image", imageUrl)
}

@Composable
fun ImageWorkout(url: String, contentDescription: String?, width: Int, height: Int) {
    val painter: Painter = rememberAsyncImagePainter(url)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
    )
}

