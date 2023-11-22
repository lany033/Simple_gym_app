package com.lifebetter.simplegymapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerExercise(
    val author_history: List<String>,
    val category: Int,
    val created: String,
    val description: String,
    val equipment: List<Int>,
    val exercise_base: Int,
    val id: Int,
    @SerialName("language")val language: Int,
    val license: Int,
    @SerialName("license_author")val licenseAuthor: String?,
    val muscles: List<Int>,
    val muscles_secondary: List<Int>,
    @SerialName("name")val name: String,
    val uuid: String,
    val variations: List<Int>
)

fun ServerExercise.toExercise() = Exercise(
    id = id,
    name = name,
    description = description,
    language = language
)