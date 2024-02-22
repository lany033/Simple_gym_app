package com.lifebetter.simplegymapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.lifebetter.simplegymapp.ui.screens.workout.WorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.exercises.ExercisesScreen
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.workout.NewRoutineScreen

fun NavGraphBuilder.workoutNavGraph(navController: NavHostController) {
    navigation(route = Graph.WORKOUT, startDestination = BottomBarNavItem.Workouts.route) {
        composable(route = BottomBarNavItem.Workouts.route) {
            WorkoutScreen(
                onClickNewRoutines = { navController.navigate(WorkoutScreens.NewRoutine.route) },
                onClickStartRoutine = { navController.navigate(WorkoutScreens.StartRoutine.route)}
            )
        }
        composable(route = WorkoutScreens.NewRoutine.route) {
            NewRoutineScreen(
                onCancel = {navController.popBackStack()},
                onClickAddExercises = {
                    navController.navigate(WorkoutScreens.AddExercise.route)
                }
            )
        }
        composable(route = WorkoutScreens.AddExercise.route) {
            ExercisesScreen(onScreenAddExercises = { navController.popBackStack() })
        }
        composable(route = WorkoutScreens.StartRoutine.route){
            LogWorkoutScreen(onFinish = {navController.popBackStack()})
        }
    }
}

sealed class WorkoutScreens(val route: String) {
    object AddExercise : WorkoutScreens("ADDEXERCISE")
    object NewRoutine : WorkoutScreens("NEWROUTINE")
    object StartRoutine : WorkoutScreens("StartRoutine")
}