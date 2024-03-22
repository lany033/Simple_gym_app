package com.lifebetter.simplegymapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lifebetter.simplegymapp.ui.screens.CalendarScreen
import com.lifebetter.simplegymapp.ui.screens.MeasuresScreen
import com.lifebetter.simplegymapp.ui.screens.ProfileScreen
import com.lifebetter.simplegymapp.ui.screens.StatisticsScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(route = Graph.PROFILE, startDestination = BottomBarNavItem.Profile.route) {
        composable(route = BottomBarNavItem.Profile.route) {
            ProfileScreen(
                onClickMeasures = { navController.navigate(ProfileScreens.Measures.route) },
                onClickStatistics = { navController.navigate(ProfileScreens.Statistics.route) },
                onClickCalendar = { navController.navigate(ProfileScreens.Calendar.route) }
            )
        }
        composable(route = ProfileScreens.Measures.route){
            MeasuresScreen()
        }
        composable(route = ProfileScreens.Statistics.route){
            StatisticsScreen()
        }
        composable(route = ProfileScreens.Calendar.route){
            CalendarScreen()
        }
    }

}

sealed class ProfileScreens(val route: String) {
    object Exercises : ProfileScreens(route = "EXERCISES")
    object Measures : ProfileScreens(route = "MEASURES")
    object Statistics : ProfileScreens(route = "STATISTICS")
    object Calendar : ProfileScreens(route = "CALENDAR")

}

