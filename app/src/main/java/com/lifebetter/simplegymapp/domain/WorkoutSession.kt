package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.domain.WorkoutSession as WorkoutSessionDomain
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

fun WorkoutSession.toWorkoutSessionDomain() : WorkoutSessionDomain = WorkoutSessionDomain(
    id = workoutSessionId,
    setWorkout = setWorkout,
    nameWorkout = nameWorkout,
    sumKg = sumKg,
    sumRep = sumRep,
    uri = uri,
    date = date,
    timer = timer
)

fun WorkoutSessionDomain.fromWorkoutSessionDomain() : WorkoutSession = WorkoutSession(
    workoutSessionId = id,
    setWorkout = setWorkout,
    nameWorkout = nameWorkout,
    sumKg = sumKg,
    sumRep = sumRep,
    uri = uri,
    date = date,
    timer = timer
)