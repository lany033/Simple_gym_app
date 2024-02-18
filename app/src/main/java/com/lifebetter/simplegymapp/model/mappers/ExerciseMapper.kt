package com.lifebetter.simplegymapp.model.mappers

import android.provider.MediaStore
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.model.remotedata.ExerciseDto
import com.lifebetter.simplegymapp.model.remotedata.items.Equipment
import com.lifebetter.simplegymapp.model.remotedata.items.Image
import com.lifebetter.simplegymapp.model.remotedata.items.Muscle
import com.lifebetter.simplegymapp.ui.screens.exercises.Muscles

/*
fun ExerciseEntity.toExercise(): Exercise {
    return Exercise(
        name = name,
        description = description
    )
}
 */
/*
fun ExerciseDto.toExercise() = ExerciseDto(
    name = name,
    description = description,
    images = images,
    muscles = muscles
)

 */


//fun List<ExerciseDto>.toLocalModel(): List<Exercise> = map { it.toLocalModel() }

fun ExerciseDto.toLocalModel(): Exercise = Exercise(

    name = name,
    description = description,
    images = images.toImage(),
    muscles = muscles,
    language = language,
    id = id,
    equipment = equipment

)

val icono = "https://static.thenounproject.com/png/3347062-200.png"
fun List<Image>.toImage(): String = map { it.image }.joinToString{it}.ifEmpty { icono }

fun List<Muscle>.toText(): String = map { it.name }.joinToString { it }

fun List<Muscles>.toEquipmentText(): String = map { it.name }.joinToString { it }

fun List<Equipment>.toTextEquipment(): String = map { it.name }.joinToString { it }

