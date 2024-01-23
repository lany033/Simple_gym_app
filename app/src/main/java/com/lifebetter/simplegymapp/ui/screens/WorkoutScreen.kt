package com.lifebetter.simplegymapp.ui.screens

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.model.getWorkouts
import com.lifebetter.simplegymapp.ui.components.CardWorkout
import com.lifebetter.simplegymapp.ui.components.CommonAccordion
import com.lifebetter.simplegymapp.ui.components.CommonButtonHome
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopAppBar
import compose.icons.FeatherIcons
import compose.icons.feathericons.Clipboard
import compose.icons.feathericons.Search


@Composable
fun WorkoutScreen(onClickNewRoutines: () -> Unit) {

    Scaffold(topBar = { MyTopAppBar("Home") }) { padding ->
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
                    onclick = {onClickNewRoutines()})
                Spacer(modifier = Modifier.size(7.dp))
                CommonButtonHome(
                    text = "Explore",
                    modifier = Modifier.weight(1f),
                    imageVector = FeatherIcons.Search,
                    onclick = {}
                )
            }
            CommonAccordion()
        }
    }

}





