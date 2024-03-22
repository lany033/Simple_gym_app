package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import android.view.RoundedCorner
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.ui.components.CommonDivider
import com.lifebetter.simplegymapp.ui.components.CommonMediumText
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar
import com.lifebetter.simplegymapp.ui.theme.Gray40

@Composable
fun ExercisesScreen(onScreenAddExercises: () -> Unit) {

    val exerciseViewModel: ExerciseViewModel =
        hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val searchText: String by exerciseViewModel.searchText.collectAsState()
    val exerciseList by exerciseViewModel.exerciseListState.collectAsState()
    val showButton by exerciseViewModel.showAddButton.collectAsState()
    val selectedExercise by exerciseViewModel.selectedExercises.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "Exercises") }) { paddingValues ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextField(
                value = searchText,
                onValueChange = exerciseViewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth().padding(14.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                },
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Gray40,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Gray40,
                    cursorColor = Color.Green
                ),
                shape = RoundedCornerShape(4.dp)
            )

            Text(text = "Popular exercises", modifier = Modifier.padding(14.dp))

            Box(modifier = Modifier.fillMaxSize().padding(14.dp)) {
                LazyColumn {
                    items(exerciseList.exerciseList) {
                        ExerciseItem(
                            nameExercise = it.name,
                            muscle = it.muscles.toText(),
                            imageUrl = it.images,
                            description = it.name,
                            id = it.id,
                            width = 50,
                            height = 50,
                            onExerciseClick = exerciseViewModel::onShowButtonAddExercise
                        )
                        CommonDivider()
                    }
                }
                if (showButton.showButtonAddExercise) {
                    Button(
                        onClick = {
                            showButton.exerciseSelected?.let { selectedExercise.add(it) }
                            onScreenAddExercises()
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                            .animateContentSize()
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(text = "Add 1 exercise")
                    }
                }
            }

        }

    }
}

@Composable
fun ExerciseItem(
    nameExercise: String,
    muscle: String,
    imageUrl: String,
    id: Int,
    description: String,
    width: Int,
    height: Int,
    onExerciseClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = { onExerciseClick(id) })
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
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
