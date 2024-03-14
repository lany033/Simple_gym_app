package com.lifebetter.simplegymapp.model.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true)
    val workoutSessionId: Int = 0,
    val setWorkout: List<SetWorkout>,
    val nameWorkout: String,
    val sumKg: Int,
    val sumRep: Int,
    val bitmap: List<Bitmap>?,
    val date: LocalDateTime?,
    val timer: Long?
)