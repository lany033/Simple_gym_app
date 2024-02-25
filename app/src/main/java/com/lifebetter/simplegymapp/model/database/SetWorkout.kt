package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SetWorkout(
    @PrimaryKey(autoGenerate = true)
    val setId:Int = 0,
    val exerciseName: String,
    val exerciseImage: String,
    val setNumber: Int,
    val kg: String,
    val rep: Int,
    val isChecked: Boolean
)
