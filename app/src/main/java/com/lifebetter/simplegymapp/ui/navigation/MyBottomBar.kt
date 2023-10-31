package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.User
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell

@Composable
fun MyBottomBar(navController: NavController) {

    NavigationBar {
        var selectedItem by remember {
            mutableStateOf(0)
        }


    }


    /*
    NavigationBar {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0 },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") })
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Dumbbell,
                    contentDescription = "Home",
                    modifier = Modifier.size(26.dp)
                )
            }, label = { Text(text = "Workouts") })
        NavigationBarItem(
            selected = index == 2,
            onClick = { index = 2 },
            icon = { Icon(imageVector = FeatherIcons.User, contentDescription = "User") },
            label = { Text(text = "Profile") })

     */
}
