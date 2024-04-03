package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.HomeScreen
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel

@Composable
fun BottomBarNavGraph(navController: NavHostController, modifier: Modifier, vm: LogWorkoutViewModel) {

    NavHost(modifier = modifier, navController = navController, startDestination = BottomBarNavItem.Home.route) {
        composable(BottomBarNavItem.Home.route) { HomeScreen() }
        workoutNavGraph(navController = navController, vm = vm)
        profileNavGraph(navController = navController)
    }
}