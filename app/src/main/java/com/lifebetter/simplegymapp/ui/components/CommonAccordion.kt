package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.database.Workout


@Composable
fun AccordionItem(workout: Workout, onDelete: (Workout)-> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            Color.Transparent
        ),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CommonTextTitle(text = workout.nameWorkout, Modifier.weight(1f))
                IconButton(onClick = { onDelete(workout) }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "more")
                }
            }
            CommonTextContent(exercises = workout.exerciseList)
            CommonButtonItems("Start Routine", {})
        }
    }
}

@Composable
fun CommonTextContent(exercises: List<Exercise>) {
    Text(text = exercises.joinToString { it.name }, color = Color.Gray)
}

@Composable
fun CommonButtonItems(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        CommonTextButtons(text = text)
    }
}




