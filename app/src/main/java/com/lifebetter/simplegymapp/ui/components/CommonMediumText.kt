package com.lifebetter.simplegymapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun CommonMediumText(text: String) {
    Text(text = text, fontSize = 15.sp)
}