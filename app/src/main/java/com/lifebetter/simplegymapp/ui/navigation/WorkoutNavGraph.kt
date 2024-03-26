package com.lifebetter.simplegymapp.ui.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.lifebetter.simplegymapp.ui.screens.workout.CameraScreen
import com.lifebetter.simplegymapp.ui.screens.SaveWorkoutSessionScreen
import com.lifebetter.simplegymapp.ui.screens.workout.WorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.exercises.ExercisesScreen
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutScreen
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel
import com.lifebetter.simplegymapp.ui.screens.workout.NewRoutineScreen

fun NavGraphBuilder.workoutNavGraph(navController: NavHostController, vm: LogWorkoutViewModel) {
    navigation(route = Graph.WORKOUT, startDestination = BottomBarNavItem.Workouts.route) {
        composable(route = BottomBarNavItem.Workouts.route) {
            WorkoutScreen(
                onClickNewRoutines = { navController.navigate(WorkoutScreens.NewRoutine.route) },
                onClickStartRoutine = {
                    navController.navigate(
                        WorkoutScreens.StartRoutine.createRouteWithId(it)
                    )
                }
            )

        }
        composable(route = WorkoutScreens.NewRoutine.route) {
            NewRoutineScreen(
                onCancel = { navController.popBackStack() },
                onClickAddExercises = {
                    navController.navigate(WorkoutScreens.AddExercise.route)
                },
                onBack = {navController.popBackStack()},

            )
        }
        composable(route = WorkoutScreens.AddExercise.route) {
            ExercisesScreen(onScreenAddExercises = { navController.popBackStack() })
        }
        composable(
            route = WorkoutScreens.StartRoutine.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            LogWorkoutScreen(
                onBack = { navController.popBackStack()},
                onFinish = { navController.navigate(WorkoutScreens.SaveRoutine.route) },
                id = id,
                vm = vm
            )
        }
        composable(route = WorkoutScreens.SaveRoutine.route) {
            SaveWorkoutSessionScreen(
                onBack = { navController.popBackStack() },
                onCamera = { navController.navigate(WorkoutScreens.Camera.route) },
                onHome = { navController.navigate(BottomBarNavItem.Home.route)},
                lwvm = vm)
        }
        composable(route = WorkoutScreens.Camera.route) {
            CameraScreen()
        }
    }
}

sealed class WorkoutScreens(val route: String) {
    object AddExercise : WorkoutScreens("ADDEXERCISE")
    object NewRoutine : WorkoutScreens("NEWROUTINE")
    object StartRoutine : WorkoutScreens("STARTROUTINE/{id}") {
        fun createRouteWithId(id: Int): String {
            Log.d("id en workout", "$id")
            return "STARTROUTINE/$id"
        }
    }

    object SaveRoutine : WorkoutScreens("SAVEROUTINE")
    object Camera : WorkoutScreens("CAMERA")
}