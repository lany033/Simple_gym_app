package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.ui.screens.HomeScreen
import com.lifebetter.simplegymapp.ui.screens.WorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.exercises.SearchViewModel

@Composable
fun BottomBarNavGraph(navController: NavHostController, modifier: Modifier) {

    //val viewModel: SearchViewModel = viewModel{SearchViewModel(ExercisesRepository())}

    NavHost(modifier = modifier, navController = navController, startDestination = BottomBarNavItem.Home.route) {
        composable(BottomBarNavItem.Home.route) { HomeScreen() }
        //composable(BottomBarNavItem.Workouts.route) { WorkoutScreen() }
        workoutNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}