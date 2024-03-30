package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.domain.Exercise

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nameWorkout: String,
    val exerciseList: List<Exercise>
)
