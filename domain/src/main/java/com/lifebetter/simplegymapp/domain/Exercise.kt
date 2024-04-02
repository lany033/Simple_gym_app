package com.lifebetter.simplegymapp.domain

data class Exercise(

    val name: String,
    val description: String,
    val images: String,
    val muscles: List<Muscle>,
    val language: Language,
    val id: Int,
    val equipment: List<Equipment>

)
