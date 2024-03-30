package com.lifebetter.simplegymapp.domain


data class Workout(
    val id: Int = 0,
    val nameWorkout: String,
    val exerciseList: List<Exercise>
)

