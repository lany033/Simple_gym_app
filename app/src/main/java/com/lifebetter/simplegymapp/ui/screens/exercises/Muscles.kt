package com.lifebetter.simplegymapp.ui.screens.exercises

import com.lifebetter.simplegymapp.R

//exercises must be in a List
data class Muscles(val name: String, val image: Int, val id: Int)

fun getMuscles(): List<Muscles> {
    return listOf(
        Muscles("All equipment", R.drawable.all,11 ),
        Muscles("Shoulders", R.drawable.shoulders,1 ),
        Muscles("Biceps", R.drawable.biceps,2),
        Muscles("Hamstrings", R.drawable.hamstrings,3),
        Muscles("Calves", R.drawable.calves,4 ),
        Muscles("Glutes", R.drawable.glutes,5 ),
        Muscles("Chest", R.drawable.chest,7 ),
        Muscles("Quads", R.drawable.quadriceps,8 ),
        Muscles("Abs", R.drawable.abs,9 ),
        Muscles("Triceps", R.drawable.triceps,10 )
    )
}