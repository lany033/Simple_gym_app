package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextButtons(text: String) {
    Text(
        modifier = Modifier.padding(5.dp),
        text = text,
        fontSize = 16.sp
    )
}