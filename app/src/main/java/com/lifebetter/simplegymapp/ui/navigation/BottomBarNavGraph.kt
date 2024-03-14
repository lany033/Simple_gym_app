package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.HomeScreen

@Composable
fun BottomBarNavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(modifier = modifier, navController = navController, startDestination = BottomBarNavItem.Home.route) {
        composable(BottomBarNavItem.Home.route) { HomeScreen() }
        workoutNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}