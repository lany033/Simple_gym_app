package com.lifebetter.simplegymapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lifebetter.simplegymapp.ui.screens.MeasuresScreen
import com.lifebetter.simplegymapp.ui.screens.ProfileScreen
import com.lifebetter.simplegymapp.ui.screens.exercises.ExercisesScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(route = Graph.PROFILE, startDestination = BottomBarNavItem.Profile.route) {
        composable(route = BottomBarNavItem.Profile.route) {
            ProfileScreen(
                onClickExercises = { navController.navigate(ProfileScreens.Exercises.route) },
                onClickMeasures = { navController.navigate(ProfileScreens.Measures.route) }
            )
        }
        composable( route = ProfileScreens.Exercises.route ) { ExercisesScreen() }
        composable( route = ProfileScreens.Measures.route ) { MeasuresScreen() }
    }
}

sealed class ProfileScreens(val route:String){
    object Exercises: ProfileScreens(route = "EXERCISES")
    object Measures: ProfileScreens(route = "MEASURES")

}

