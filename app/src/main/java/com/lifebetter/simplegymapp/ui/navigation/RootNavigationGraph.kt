package com.lifebetter.simplegymapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lifebetter.simplegymapp.ui.screens.HomeScreen
import com.lifebetter.simplegymapp.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, route = Graph.ROOT, startDestination = Graph.HOME){
        composable(route = Graph.HOME){ MainScreen() }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val WORKOUT = "workout_graph"
    const val PROFILE = "profile_graph"
}