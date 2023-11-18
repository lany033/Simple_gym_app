package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.HomeScreen
import com.lifebetter.simplegymapp.ui.screens.WorkoutScreen

@Composable
fun BottomBarNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarNavItem.Home.route) {
        composable(BottomBarNavItem.Home.route) { HomeScreen() }
        composable(BottomBarNavItem.Workouts.route) { WorkoutScreen() }
        //profileScreen(navController = navController) { exerciseScreen() }
        profileNavGraph(navController = navController)
    }
}


/*
fun NavGraphBuilder.profileScreen(
    navController: NavHostController,
    nestedGraph: NavGraphBuilder.() -> Unit
) = navigation(route = Graph.PROFILE, startDestination = BottomBarNavItem.Profile.route) {
        composable(route = BottomBarNavItem.Profile.route) {
            ProfileScreen(
                onClick = { navController.navigate(Graph.DASHBOARD) }
            )
        }
    nestedGraph()
}

const val ExercisesRoute = "exercises_route"
const val Measures = "measures"

fun NavGraphBuilder.exerciseScreen() = navigation(route = ExercisesRoute, startDestination = Graph.DASHBOARD) { composable(Graph.DASHBOARD) { ExercisesScreen() } }
 */




