package com.lifebetter.simplegymapp.data.test

data class Result(
    val aliases: List<Any>,
    val author_history: List<String>,
    val category: Category,
    val comments: List<Comment>,
    val created: String,
    val description: String,
    val equipment: List<Equipment>,
    val exercise_base_id: Int,
    val id: Int,
    val images: List<Image>,
    val language: Language,
    val license: License,
    val license_author: String,
    val muscles: List<Muscle>,
    val muscles_secondary: List<MusclesSecondary>,
    val name: String,
    val uuid: String,
    val variations: List<Int>,
    val videos: List<Video>
)