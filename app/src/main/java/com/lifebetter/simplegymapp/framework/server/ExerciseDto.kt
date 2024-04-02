package com.lifebetter.simplegymapp.framework.server

import com.lifebetter.simplegymapp.domain.Equipment
import com.lifebetter.simplegymapp.domain.Image
import com.lifebetter.simplegymapp.domain.Language
import com.lifebetter.simplegymapp.domain.Muscle


data class ExerciseDto(

    val description: String,
    val equipment: List<Equipment>,
    val id: Int,
    val images: List<Image>,
    val language: Language,
    val muscles: List<Muscle>,
    val name: String,

)


