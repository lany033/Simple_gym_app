package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.model.data.test.Language

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
)
