package com.lifebetter.simplegymapp.ui.screens.exercises

import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.lifebetter.simplegymapp.model.mappers.toText
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonDivider
import com.lifebetter.simplegymapp.ui.components.CommonMediumText
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithOneText
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(onScreenAddExercises: () -> Unit) {
    val scope = rememberCoroutineScope()

    val sheetEquipmentState = rememberModalBottomSheetState()
    var showBottomEquipmentSheet by remember { mutableStateOf(false) }

    val sheetMuscleState = rememberModalBottomSheetState()
    var showBottomMuscleSheet by remember { mutableStateOf(false) }

    val exerciseViewModel: ExerciseViewModel = hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val searchText: String by exerciseViewModel.searchText.collectAsState()
    val exerciseList by exerciseViewModel.exerciseListState.collectAsState()
    val showButton by exerciseViewModel.showAddButton.collectAsState()
    val selectedExercise by exerciseViewModel.selectedExercises.collectAsState()

    Scaffold(topBar = { MyTopWithIconsBar(title = "Exercises") }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = exerciseViewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search") },
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors()
            )

            Row() {
                Button(
                    onClick = { showBottomEquipmentSheet = true },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All equipment", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.size(15.dp))
                Button(
                    onClick = { showBottomMuscleSheet = true },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .size(40.dp)
                ) {
                    Text(text = "All Muscles", fontSize = 16.sp)
                }
            }

            Text(text = "Ejercicios Populares")

            Box(modifier = Modifier.fillMaxSize()) {
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
                                  } ,
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
            if (showBottomEquipmentSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomEquipmentSheet = false
                    },
                    sheetState = sheetEquipmentState
                ) {
                    // Sheet content
                    MyTopBarWithOneText(
                        title = "Tipo de categoria",
                        subtitleTwo = "Cancel",
                        onClickCancel = {
                            scope.launch { sheetEquipmentState.hide() }.invokeOnCompletion {
                                if (!sheetEquipmentState.isVisible) {
                                    showBottomEquipmentSheet = false
                                }
                            }
                        }
                    )
                    LazyColumn {
                        items(getEquipments()) {
                            CommonDivider()
                            EquipmentItem(
                                nameEquipment = it.name,
                                imageEquipment = it.image,
                                id = it.id,
                                onClickEquipment = exerciseViewModel::onFilterByEquipment)
                        }
                    }

                }

            }

            if (showBottomMuscleSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomMuscleSheet = false
                    },
                    sheetState = sheetMuscleState
                ) {
                    // Sheet content
                    MyTopBarWithOneText(
                        title = "Grupo Muscular",
                        subtitleTwo = "Cancel",
                        onClickCancel = {
                            scope.launch { sheetMuscleState.hide() }.invokeOnCompletion {
                                if (!sheetMuscleState.isVisible) {
                                    showBottomMuscleSheet = false
                                }
                            }
                        }
                    )
                    LazyColumn {
                        items(getMuscles()) {
                            CommonDivider()
                            EquipmentItem(
                                nameEquipment = it.name,
                                imageEquipment = it.image,
                                id = it.id,
                                onClickEquipment = exerciseViewModel::onFilterByMuscle)
                        }
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

@Composable
fun EquipmentItem(nameEquipment: String, imageEquipment: Int, onClickEquipment: (Int) -> Unit, id:Int) {
    Card(
        modifier = Modifier
            .clickable(onClick = { onClickEquipment(id) })
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CommonCirclePhoto(painter = imageEquipment, size = 56)
            Spacer(modifier = Modifier.size(20.dp))
            Text(text = nameEquipment)
        }
    }
}
