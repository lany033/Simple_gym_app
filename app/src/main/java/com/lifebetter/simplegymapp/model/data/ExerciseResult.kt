package com.lifebetter.simplegymapp.model.data

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResult(
    val count: Int,
    val next: String,
    //val previous: Any,
    val results: List<ServerExercise>
)

//https://wger.de/api/v2/exercise/?limit=20&offset=20
