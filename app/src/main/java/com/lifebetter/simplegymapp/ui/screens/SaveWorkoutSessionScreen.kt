package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.ui.components.CommonTextTitle
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithBackIcon
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Image

@Composable
fun SaveWorkoutSessionScreen() {
    Scaffold(topBar = {
        MyTopBarWithBackIcon(
            title = "Save Workout",
            subtitleTwo = "Save",
            onClickCancel = { /*TODO*/ },
            onClickSave = {})
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CommonTextTitle(text = "Test", modifier = Modifier)
            Row {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Duration")
                    Text(text = "32 min", fontSize = 20.sp, color = Color.Blue)
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Volume")
                    Text(text = "1230 kg", fontSize = 20.sp)

                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Sets")
                    Text(text = "6", fontSize = 20.sp)
                }
            }
            Divider()
            Card(
                modifier = Modifier.fillMaxWidth(), shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Text(text = "When")
                Text(text = "Martes 05", fontSize = 20.sp, color = Color.Blue)
            }
            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable { },
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Icon(imageVector = FontAwesomeIcons.Solid.Image, contentDescription = null, tint = Color.Blue)
                    }
                }
                Text(text = "Add a photo", fontSize = 18.sp)
            }



            Divider()
            Card(
                modifier = Modifier.fillMaxWidth(), shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Description")
                Text(text = "Write fkjfnkfkfnsf", fontSize = 20.sp)
            }
        }


    }
}