package com.lifebetter.simplegymapp.model.remotedata

import com.lifebetter.simplegymapp.model.remotedata.items.Equipment
import com.lifebetter.simplegymapp.model.remotedata.items.Image
import com.lifebetter.simplegymapp.model.remotedata.items.Language
import com.lifebetter.simplegymapp.model.remotedata.items.Muscle
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class ExerciseDto(
    //val aliases: List<String>,
    //val author_history: List<String>,
    //val category: Category,
    //val comments: List<String>,
    //val created: String,
    val description: String,
    val equipment: List<Equipment>,
    //val exercise_base_id: Int,
    //@SerialName("id")val id: Int,
    val images: List<Image>,
    //val language: Language,
    //val license: String,
    //val license_author: String,
    val muscles: List<Muscle>,
    //val muscles_secondary: List<String>,
    val name: String,
    //val uuid: String,
    //val variations: List<Int>,
    //val videos: List<String>
)


