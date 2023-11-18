package com.lifebetter.simplegymapp.data

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResult(
    val count: Int,
    val next: String,
    //val previous: Any,
    val results: List<ServerExercise>
)