package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen() {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable {
        mutableStateOf(false)
    }

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
                Button(onClick = { showBottomSheet = true }, shape = RoundedCornerShape(4.dp), modifier = Modifier
                    .weight(1f)
                    .size(40.dp)) {
                    Text(text = "All equipment", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.size(15.dp))
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(4.dp), modifier = Modifier
                    .weight(1f)
                    .size(40.dp)) {
                    Text(text = "All Muscles", fontSize = 16.sp)
                }
            }

            if (showBottomSheet){
                ModalBottomSheet(onDismissRequest = { showBottomSheet = false }, sheetState = sheetState) {
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                            Text(text = "Equipment", fontSize = 16.sp, modifier = Modifier.padding(15.dp))
                        }
                        Divider(color = Color.LightGray, thickness = 0.5.dp)
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text("Hide bottom sheet")
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                    }

                }
            }

            Text(text = "Results")
            LazyColumn {

                items(state.exercise.size){i ->
                    val exercise = state.exercise[i]
                    if ( i >= state.exercise.size - 1 && !state.endReached && !state.isLoading){
                        viewModel.loadNextItem()
                    }
                    if (exercise.language.id == 2){
                        Text(text = "Exercise: ${exercise.name}")
                    }
                }
                item {
                    if (state.isLoading){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

