package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String
)


