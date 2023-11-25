package com.lifebetter.simplegymapp.model.remotedata

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val description: String
)
