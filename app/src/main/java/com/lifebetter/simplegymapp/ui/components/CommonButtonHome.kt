package com.lifebetter.simplegymapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun CommonButtonHome(text: String, modifier: Modifier, imageVector: ImageVector) {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(4.dp),
        modifier = modifier,
        //contentPadding = PaddingValues(start = 7.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "new")
        CommonTextButtons(text = text)
    }
}