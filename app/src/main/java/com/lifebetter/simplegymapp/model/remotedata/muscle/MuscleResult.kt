package com.lifebetter.simplegymapp.model.remotedata.muscle

data class MuscleResult(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<MuscleDto>
)