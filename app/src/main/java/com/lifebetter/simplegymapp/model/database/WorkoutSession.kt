package com.lifebetter.simplegymapp.model.database

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class WorkoutSession(
    @PrimaryKey(autoGenerate = true)
    val workoutSessionId: Int = 0,
    val setWorkout: List<SetWorkout>,
    val nameWorkout: String,
    val sumKg: Int,
    val sumRep: Int,
    val uri: List<String>?,
    val date: LocalDateTime = LocalDateTime.now(),
    val timer: Long?
){
    val createdDateFormatted : String
        get() =date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

}