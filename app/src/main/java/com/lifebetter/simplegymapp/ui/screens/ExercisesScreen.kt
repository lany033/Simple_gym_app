package com.lifebetter.simplegymapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.ui.components.CommonButtonHome
import com.lifebetter.simplegymapp.ui.components.CommonTextButtons
import com.lifebetter.simplegymapp.ui.components.MyTopWithIconsBar

@Composable
fun ExercisesScreen() {
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
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(4.dp), modifier = Modifier
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
        }
    }
}