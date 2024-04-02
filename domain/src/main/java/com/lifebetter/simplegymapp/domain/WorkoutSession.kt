package com.lifebetter.simplegymapp.domain


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WorkoutSession(
    val id: Int,
    val setWorkout: List<SetWorkout>,
    val nameWorkout: String,
    val sumKg: Int,
    val sumRep: Int,
    val uri: List<String>?,
    val date: LocalDateTime = LocalDateTime.now(),
    val timer: Long
){
    val createdDateFormatted : String
        get() =date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

}
