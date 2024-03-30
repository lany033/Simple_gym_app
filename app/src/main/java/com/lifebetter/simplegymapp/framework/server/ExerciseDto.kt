package com.lifebetter.simplegymapp.framework.server

import com.lifebetter.simplegymapp.domain.Equipment
import com.lifebetter.simplegymapp.domain.Image
import com.lifebetter.simplegymapp.domain.Language
import com.lifebetter.simplegymapp.domain.Muscle


data class ExerciseDto(
    //val aliases: List<String>,
    //val author_history: List<String>,
    //val category: Category,
    //val comments: List<String>,
    //val created: String,
    val description: String,
    val equipment: List<Equipment>,
    //val exercise_base_id: Int,
    val id: Int,
    val images: List<Image>,
    val language: Language,
    //val license: String,
    //val license_author: String,
    val muscles: List<Muscle>,
    //val muscles_secondary: List<String>,
    val name: String,
    //val uuid: String,
    //val variations: List<Int>,
    //val videos: List<String>
)


