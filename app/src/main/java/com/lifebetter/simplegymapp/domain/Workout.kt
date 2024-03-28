package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.domain.Workout as WorkoutDomain

data class Workout(
    val id: Int,
    val nameWorkout: String,
    val exerciseList: List<Exercise>
)


fun Workout.toWorkoutDomain(): WorkoutDomain = WorkoutDomain(
    id = id,
    nameWorkout = nameWorkout,
    exerciseList = exerciseList
)

fun WorkoutDomain.fromWorkoutDomain(): Workout = Workout(
    id = id,
    nameWorkout = nameWorkout,
    exerciseList = exerciseList
)