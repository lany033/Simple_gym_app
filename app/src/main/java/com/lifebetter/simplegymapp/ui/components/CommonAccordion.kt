package com.lifebetter.simplegymapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.model.getWorkouts
import compose.icons.FeatherIcons
import compose.icons.feathericons.MoreHorizontal

@Composable
fun CommonAccordion() {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        AccordionHeader()
        AnimatedVisibility(visible = true) {
            Surface(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                LazyColumn {
                    items(getWorkouts()) { cardItem ->
                        AccordionItem(cardItem.name, cardItem.exercises)
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AccordionHeader() {
    Surface(

    ) {
        Row {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "arrowRight")
            Text(text = "My Routines ()")
        }
    }
}

@Composable
fun AccordionItem(workoutName: String, exercises: String) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CommonTextTitle(text = workoutName, Modifier.weight(1f))
                Icon(imageVector = FeatherIcons.MoreHorizontal, contentDescription = "more")
            }
            CommonTextContent(exercises)
            CommonButtonItems("Start Routine")
        }
    }
}

@Composable
fun CommonTextContent(exercises: String) {
    Text(text = exercises)
}

@Composable
fun CommonButtonItems(text: String) {
    Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {
        CommonTextButtons(text = text)
    }
}




