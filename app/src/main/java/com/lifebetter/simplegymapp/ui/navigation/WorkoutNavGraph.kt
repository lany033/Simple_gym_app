package com.lifebetter.simplegymapp.ui.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lifebetter.simplegymapp.ui.screens.WorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.exercises.ExercisesScreen
import com.lifebetter.simplegymapp.ui.screens.newroutine.NewRoutineScreen

fun NavGraphBuilder.workoutNavGraph(navController: NavHostController) {
    navigation(route = Graph.WORKOUT, startDestination = BottomBarNavItem.Workouts.route) {
        composable(route = BottomBarNavItem.Workouts.route) {
            WorkoutScreen(
                onClickNewRoutines = { navController.navigate(WorkoutScreens.NewRoutine.route) }
            )
        }
        composable(route = WorkoutScreens.NewRoutine.route) { entry ->
            val exerciseId = entry.savedStateHandle.get<Int>("ExerciseId")
            Log.d("exercise id en workout screen", exerciseId.toString())
            NewRoutineScreen(
                exerciseId = exerciseId,
                onClickAddExercises = {
                    navController.navigate(WorkoutScreens.AddExercise.route)

                }
            )
        }
        composable(
            route = WorkoutScreens.AddExercise.route
        ) {
            ExercisesScreen(
                onScreenAddExercises = {
                    navController.popBackStack()
                    navController.currentBackStackEntry?.savedStateHandle?.set("ExerciseId", it)
                    Log.d("id en exercise screen", it.toString())
                }
            )
        }
    }
}

sealed class WorkoutScreens(val route: String) {
    object AddExercise : WorkoutScreens("ADDEXERCISE")
    object NewRoutine : WorkoutScreens("NEWROUTINE")
    object Explore : WorkoutScreens("EXPLORE")
}