package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.R
import com.lifebetter.simplegymapp.model.getWorkouts
import com.lifebetter.simplegymapp.ui.components.CommonCirclePhoto
import com.lifebetter.simplegymapp.ui.components.CommonDivider
import com.lifebetter.simplegymapp.ui.components.CommonLittleText
import com.lifebetter.simplegymapp.ui.components.CommonMediumText
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import compose.icons.FeatherIcons
import compose.icons.feathericons.MoreHorizontal
import compose.icons.feathericons.Share

@Composable
fun WorkoutScreen() {
    Column {
        LazyColumn {
            items(getWorkouts()) { workout ->
                CardWorkout(workout.name)
            }
        }
    }
}

@Composable
fun CardWorkout(workoutName: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.align(Alignment.TopStart), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    CommonCirclePhoto(painter = R.drawable.perfilphoto, size = 50)
                    Column {
                        CommonMediumText(text = "lany033")
                        CommonLittleText(text = "Today (fecha)")
                    }
                }
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd) ) {
                    Icon(
                        imageVector = FeatherIcons.MoreHorizontal,
                        contentDescription = "more"
                    )
                }
            }

            CommonTextTitle(text = workoutName, modifier = Modifier.padding(2.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                Column {
                    CommonLittleText(text = "Time")
                    CommonMediumText(text = "1h 3min")
                }
                Column {
                    CommonLittleText(text = "Volume")
                    CommonMediumText(text = "2,256 kg")
                }
                Column {
                    CommonLittleText(text = "Records")
                    CommonMediumText(text = "1")
                }
            }
            CommonDivider()
            Box {
            }
            CommonDivider()
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(
                    Alignment.TopCenter)) {
                    Icon(
                        imageVector = FeatherIcons.Share,
                        contentDescription = "share"
                    )
                }
            }
        }
    }
}




