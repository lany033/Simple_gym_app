package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.model.database.Exercise
import com.lifebetter.simplegymapp.domain.Exercise as ExerciseDomain
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto
import com.lifebetter.simplegymapp.model.remotedata.items.Image
import com.lifebetter.simplegymapp.model.remotedata.items.Muscle
import com.lifebetter.simplegymapp.model.database.Workout
import com.lifebetter.simplegymapp.model.database.WorkoutSession
import com.lifebetter.simplegymapp.domain.Workout as WorkoutDomain
import com.lifebetter.simplegymapp.domain.WorkoutSession as WorkoutSessionDomain

fun Long.formatTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

fun ExerciseDto.toDomain(): ExerciseDomain = ExerciseDomain(
    name = name,
    description = description,
    images = images.toImage(),
    muscles = muscles,
    language = language,
    id = id,
    equipment = equipment

)

fun Exercise.toExerciseDomain(): ExerciseDomain = ExerciseDomain(
    name = name,
    description = description,
    images = images,
    muscles = muscles,
    language = language,
    id = id,
    equipment = equipment
)

fun ExerciseDomain.toExerciseDatabase(): Exercise = Exercise(
    name = name,
    description = description,
    images = images,
    muscles = muscles,
    language = language,
    id = id,
    equipment = equipment
)

fun List<Exercise>.toLocalModel(): List<ExerciseDomain> = map { it.toExerciseDomain() }
fun List<ExerciseDomain>.fromLocalModel(): List<Exercise> = map { it.toExerciseDatabase() }

fun Workout.toWorkoutDomain(): WorkoutDomain =
    WorkoutDomain(
        id = id,
        nameWorkout = nameWorkout,
        exerciseList = exerciseList
    )

fun WorkoutDomain.fromWorkoutDomain(): Workout = Workout(
    id = id,
    nameWorkout = nameWorkout,
    exerciseList = exerciseList
)

fun List<Workout>.toWorkoutDomain(): List<WorkoutDomain> = map { it.toWorkoutDomain() }
fun List<WorkoutDomain>.fromWorkoutDomain(): List<Workout> = map { it.fromWorkoutDomain() }

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

fun List<WorkoutSession>.toWorkoutSessionDomain(): List<WorkoutSessionDomain> = map { it.toWorkoutSessionDomain() }
fun List<WorkoutSessionDomain>.fromWorkoutSessionDomain(): List<WorkoutSession> = map { it.fromWorkoutSessionDomain() }


val icono = "https://static.thenounproject.com/png/3347062-200.png"
fun List<Image>.toImage(): String = map { it.image }.joinToString{it}.ifEmpty { icono }

fun List<Muscle>.toText(): String = map { it.name }.joinToString { it }


