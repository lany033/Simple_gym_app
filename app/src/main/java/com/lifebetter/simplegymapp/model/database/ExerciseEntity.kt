package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity

@Entity
data class ExerciseEntity(
    val id: Int,
    val name: String,
    val description: String
)
