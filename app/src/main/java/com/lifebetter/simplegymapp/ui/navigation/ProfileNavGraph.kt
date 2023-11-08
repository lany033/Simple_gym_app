package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.ExercisesScreen

@Composable
fun ProfileNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "profile"){
        composable(route = ProfileScreens.Exercises.route) { ExercisesScreen() }
    }
}

sealed class ProfileScreens(val route:String){
    object Exercises: ProfileScreens(route = "EXERCISES")
}