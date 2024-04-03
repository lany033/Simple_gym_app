package com.lifebetter.simplegymapp.framework.server

data class ExerciseResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ExerciseDto>
)
