package com.lifebetter.simplegymapp.model.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true)
    val workoutSessionId: Int = 0,
    val nameWorkout: String,
    val sumKg: Int,
    val sumRep: Int,
    val bitmap: String,
    val date: String?,
    val timer: Long?
)