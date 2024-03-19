package com.lifebetter.simplegymapp.ui.screens.workout

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.ui.components.AccordionItem
import com.lifebetter.simplegymapp.ui.components.CommonButtonHome
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.Clipboard
import compose.icons.feathericons.Search
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Smile


@Composable
fun WorkoutScreen(onClickNewRoutines: () -> Unit, onClickStartRoutine: (Int) -> Unit) {

    val workoutViewModel: WorkoutViewModel = hiltViewModel()

    val workoutListState by workoutViewModel.workoutListState.collectAsState()

    Scaffold(topBar = { MyTopAppBar("Workout") }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(14.dp)
        ) {
            CommonTextTitle("Quick Start", Modifier.padding(top = 5.dp, bottom = 5.dp))
            CommonButtonHome(
                text = "Start Empty Workout",
                modifier = Modifier.fillMaxWidth(),
                imageVector = Icons.Filled.Add,
                onclick = {}
            )
            CommonTextTitle(text = "Routines", Modifier.padding(top = 5.dp, bottom = 5.dp))
            Row {
                CommonButtonHome(
                    "New Routine",
                    Modifier.weight(1f),
                    FeatherIcons.Clipboard,
                    onclick = { onClickNewRoutines() })
                Spacer(modifier = Modifier.size(7.dp))
                CommonButtonHome(
                    text = "Explore",
                    modifier = Modifier.weight(1f),
                    imageVector = FeatherIcons.Search,
                    onclick = {}
                )
            }

            if (workoutListState.workoutList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Empieza agregando una nueva rutina.",
                        modifier = Modifier
                            .padding(14.dp)
                            .align(
                                Alignment.Center
                            ),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Smile,
                        tint = Color.Gray,
                        contentDescription = "icon",
                        modifier = Modifier
                            .size(30.dp)
                            .align(
                                Alignment.BottomCenter
                            )
                    )
                }

            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!workoutListState.openAccordion) {
                        IconButton(onClick = workoutViewModel::onOpenAccordion) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowRight,
                                contentDescription = "arrowRight"
                            )
                        }
                    } else {
                        IconButton(onClick = workoutViewModel::offOpenAccordion) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = "arrowRight"
                            )
                        }
                    }

                    Text(text = "My Routines ( ${workoutListState.workoutList.size} )")
                }

                AnimatedVisibility(visible = workoutListState.openAccordion) {
                    LazyColumn(modifier = Modifier.padding(14.dp)) {
                        items(workoutListState.workoutList) {
                            AccordionItem(
                                workout = it,
                                onDelete = workoutViewModel::deleteWorkout,
                                onStartRoutine = { onClickStartRoutine(it.id) })
                            Spacer(modifier = Modifier.size(5.dp))
                        }
                    }
                }
            }


        }
    }

}





