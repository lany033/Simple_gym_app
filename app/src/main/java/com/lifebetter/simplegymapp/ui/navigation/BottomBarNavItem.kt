package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FeatherIcons
import compose.icons.FontAwesomeIcons
import compose.icons.feathericons.User
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Dumbbell

sealed class BottomBarNavItem(
    var title: String,
    var imageVector: ImageVector,
    var route: String
) {
    object Home : BottomBarNavItem("Home", Icons.Default.Home, "Home")
    object Workouts : BottomBarNavItem("Workout", FontAwesomeIcons.Solid.Dumbbell, "Workout")
    object Profile : BottomBarNavItem("Profile", FeatherIcons.User, "Profile")
}

