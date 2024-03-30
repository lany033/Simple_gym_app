package com.lifebetter.simplegymapp.model.remotedata

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

data class ExerciseResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ExerciseDto>
)

/*

//https://wger.de/api/v2/exercise/?limit=20&offset=20
fun ExerciseResult.toPage() : Int {

    val numberPage = (next?.split("/".toRegex())?.last()?.split("=".toRegex()))?.last()

    return numberPage?.toInt() ?: 0
}

 */