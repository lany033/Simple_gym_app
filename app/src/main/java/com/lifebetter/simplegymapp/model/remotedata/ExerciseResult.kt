package com.lifebetter.simplegymapp.model.remotedata

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseResult(
    val count: Int,
    val next: String,
    //val previous: Any,
    val results: List<ServerExercise>
)

//https://wger.de/api/v2/exercise/?limit=20&offset=20
fun ExerciseResult.toPage() : Int {

    val numberPage = (next.split("/".toRegex()).last().split("=".toRegex())).last()

    return numberPage.toInt()
}