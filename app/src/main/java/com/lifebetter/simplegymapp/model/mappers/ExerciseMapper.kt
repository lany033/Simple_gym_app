package com.lifebetter.simplegymapp.model.mappers

import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto

/*
fun ExerciseEntity.toExercise(): Exercise {
    return Exercise(
        name = name,
        description = description
    )
}

 */

fun List<ExerciseDto>.toLocalModel(): List<ExerciseEntity> = map { it.toLocalModel() }

fun ExerciseDto.toLocalModel(): ExerciseEntity = ExerciseEntity(
    id=0,
    name,
    description
)