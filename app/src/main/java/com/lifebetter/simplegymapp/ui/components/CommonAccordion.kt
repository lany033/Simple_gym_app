package com.lifebetter.simplegymapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.model.database.Workout
import compose.icons.FeatherIcons
import compose.icons.feathericons.MoreHorizontal


@Composable
fun AccordionItem(workout: Workout) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CommonTextTitle(text = workout.nameWorkout, Modifier.weight(1f))
                Icon(imageVector = FeatherIcons.MoreHorizontal, contentDescription = "more")
            }
            CommonButtonItems("Start Routine", {})
        }
    }
}

@Composable
fun CommonTextContent(exercises: String) {
    Text(text = exercises)
}

@Composable
fun CommonButtonItems(text: String, onClick: ()-> Unit) {
    Button(onClick = { onClick() }, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
        CommonTextButtons(text = text)
    }
}




