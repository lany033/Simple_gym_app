package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifebetter.simplegymapp.ui.components.AccordionItem
import com.lifebetter.simplegymapp.ui.components.CommonButtonHome
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar
import compose.icons.FeatherIcons
import compose.icons.feathericons.Clipboard
import compose.icons.feathericons.Search


@Composable
fun WorkoutScreen(onClickNewRoutines: () -> Unit) {
    val scope = rememberCoroutineScope()

    val workoutViewModel: WorkoutViewModel = hiltViewModel()

    val openAccordion by workoutViewModel.openAccordion.collectAsState()

    val workoutList by workoutViewModel.workoutListState.collectAsState()

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

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!openAccordion) {
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

                Text(text = "My Routines ()")
            }


            if (workoutList.workoutList.isEmpty()){
                Text(text = "Empieza añadiendo una nueva rutina")
            } else {
                AnimatedVisibility(visible = openAccordion) {
                    LazyColumn(modifier = Modifier.padding(14.dp)){
                        items(workoutList.workoutList){
                            AccordionItem(workout = it)
                        }
                    }
                }
            }


        }
    }

}





