package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.HomeScreen
import com.lifebetter.simplegymapp.ui.screens.ProfileScreen
import com.lifebetter.simplegymapp.ui.screens.WorkoutScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route){
        composable(BottomNavItem.Home.route){ HomeScreen() }
        composable(BottomNavItem.Workouts.route){ WorkoutScreen() }
        composable(BottomNavItem.Profile.route) { ProfileScreen() }
    }
}