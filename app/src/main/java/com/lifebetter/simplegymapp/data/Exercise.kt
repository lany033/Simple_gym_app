package com.lifebetter.simplegymapp.data

import com.lifebetter.simplegymapp.data.test.Language
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    @Contextual val language: Language
)
