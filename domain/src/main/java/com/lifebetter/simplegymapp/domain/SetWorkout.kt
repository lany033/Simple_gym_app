package com.lifebetter.simplegymapp.domain


data class SetWorkout(
    val exerciseName: String,
    val exerciseId: Int,
    val exerciseImage: String,
    val listSet: List<SetValue>,
)
