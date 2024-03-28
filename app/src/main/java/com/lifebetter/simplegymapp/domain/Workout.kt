package com.lifebetter.simplegymapp.domain


data class Workout(
    val id: Int,
    val nameWorkout: String,
    val exerciseList: List<Exercise>
)

