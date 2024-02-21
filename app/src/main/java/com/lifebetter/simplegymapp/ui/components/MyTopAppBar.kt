package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.database.Workout

@Composable
fun MyTopAppBar(title: String) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) { Text(text = title, fontSize = 16.sp, modifier = Modifier.padding(15.dp)) }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}


@Composable
fun MyTopWithIconsBar(title: String) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Edit Profile", fontSize = 16.sp)
            }
            Text(text = title, fontSize = 16.sp)
            Spacer(modifier = Modifier.size(2.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "settings")
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}


@Composable
fun MyTopBarWithTwoText(
    subtitleOne: String,
    title: String,
    subtitleTwo: String,
    nameTitle: String,
    listWorkout: List<Exercise>,
    onClickCancel: () -> Unit,
    onClickSave: (String,List<Exercise>) -> Unit,
) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onClickCancel() }) {
                Text(text = subtitleOne, fontSize = 16.sp)
            }
            Text(text = title, fontSize = 16.sp)
            TextButton(onClick = { onClickSave(nameTitle,listWorkout) }) {
                Text(text = subtitleTwo, fontSize = 16.sp)
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}


@Composable
fun MyTopBarWithOneText(title: String, subtitleTwo: String, onClickCancel: () -> Unit) {
    Column() {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = title, fontSize = 16.sp, modifier = Modifier.align(Alignment.Center))
            TextButton(onClick = onClickCancel, modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(text = subtitleTwo, fontSize = 16.sp)
            }
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}
