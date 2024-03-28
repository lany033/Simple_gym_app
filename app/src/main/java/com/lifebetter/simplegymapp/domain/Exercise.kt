package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.data.remotedata.items.Equipment
import com.lifebetter.simplegymapp.data.remotedata.items.Language
import com.lifebetter.simplegymapp.data.remotedata.items.Muscle

data class Exercise(

    val name: String,
    val description: String,
    val images: String,
    val muscles: List<Muscle>,
    val language: Language,
    val id: Int,
    val equipment: List<Equipment>

)
