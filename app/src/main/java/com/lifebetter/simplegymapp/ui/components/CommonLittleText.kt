package com.lifebetter.simplegymapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CommonLittleText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.LightGray)
}