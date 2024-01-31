package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.model.remotedata.items.Equipment
import com.lifebetter.simplegymapp.model.remotedata.items.Muscle

data class Exercise(

    val name: String,
    val description: String,
    val images: String,
    val muscles: List<Muscle>,
    val equipment: List<Equipment>

)
