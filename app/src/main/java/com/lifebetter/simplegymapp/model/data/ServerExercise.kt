package com.lifebetter.simplegymapp.model.data

import com.lifebetter.simplegymapp.model.data.test.Language
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerExercise(
    //val aliases: List<String>,
    //val author_history: List<String>,
    //val category: Category,
    //val comments: List<String>,
    //val created: String,
    @SerialName("description")val description: String,
    //val equipment: List<String>,
    //val exercise_base_id: Int,
    @SerialName("id")val id: Int,
    //val images: List<String>,
    @Contextual val language: Language,
    //val license: String,
    //val license_author: String,
    //val muscles: List<String>,
    //val muscles_secondary: List<String>,
    @SerialName("name")val name: String,
    val uuid: String,
    //val variations: List<Int>,
    //val videos: List<String>
)

fun ServerExercise.toExercise() = RemoteExercise(
    id = id,
    name = name,
    description = description,
    language = language
)