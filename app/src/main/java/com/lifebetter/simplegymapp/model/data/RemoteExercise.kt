package com.lifebetter.simplegymapp.model.data

import com.lifebetter.simplegymapp.model.data.test.Language
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class RemoteExercise(
    val id: Int,
    val name: String,
    val description: String,
    @Contextual val language: Language
)
