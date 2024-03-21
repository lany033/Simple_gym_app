package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.lifebetter.simplegymapp.ui.theme.Green40


@Composable
fun CommonButtonHome(text: String, modifier: Modifier, imageVector: ImageVector, onclick: () -> Unit) {
    Button(
        onClick = { onclick() },
        shape = RoundedCornerShape(4.dp),
        modifier = modifier,
        border = BorderStroke(2.dp, Color.LightGray),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Icon(imageVector = imageVector, contentDescription = "new", tint = Green40)
        CommonTextButtons(text = text)
    }
}