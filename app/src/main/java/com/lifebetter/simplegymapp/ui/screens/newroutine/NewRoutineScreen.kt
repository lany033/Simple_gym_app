package com.lifebetter.simplegymapp.ui.screens.newroutine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifebetter.simplegymapp.ui.components.CommonButtonItems
import com.lifebetter.simplegymapp.ui.components.MyTopBarWithTwoText
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewRoutineScreen(onClickAddExercises:()->Unit) {
    Scaffold(topBar = { MyTopBarWithTwoText("Cancel", "Build Routine", "Save") }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            TextField(
                value = "Titulo de la rutina",
                onValueChange = {},
                textStyle = TextStyle(fontSize = 24.sp, color = Color.Gray, fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent)
            )
            Box {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Dumbbell,
                        contentDescription = "pesa",
                        modifier = Modifier.size(26.dp)
                    )
                    Text(text = "Empieza agregando un ejercicio a tu rutina", color = Color.LightGray)
                    CommonButtonItems(text = "+ Agregar Ejercicio", onClick = {onClickAddExercises()})
                }
            }
        }

    }
}
