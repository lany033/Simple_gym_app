package com.lifebetter.simplegymapp.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CommonCirclePhoto(@DrawableRes painter: Int, size: Int) {
    Card(shape = CircleShape) {
        Image(painter = painterResource(id = painter), contentDescription = "photo", modifier = Modifier.size(size.dp), contentScale = ContentScale.Crop)
    }
}