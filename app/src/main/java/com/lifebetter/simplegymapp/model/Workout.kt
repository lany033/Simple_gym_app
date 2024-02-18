package com.lifebetter.simplegymapp.model

//exercises must be in a List
data class Workout(val name: String, val image: String, val id: Int)

/*
fun getWorkouts(): List<Workout> {
    return listOf(
        Workout("Triceps, hombro y pecho", "push up, triceps push down"),
        Workout("Triceps, hombro y pecho", "push up, triceps push down"),
        Workout("Triceps, hombro y pecho", "push up, triceps push down"),
        Workout("Triceps, hombro y pecho", "push up, triceps push down")
    )
}

 */