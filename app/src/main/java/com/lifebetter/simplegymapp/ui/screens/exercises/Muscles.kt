package com.lifebetter.simplegymapp.ui.screens.exercises

import com.lifebetter.simplegymapp.R

//exercises must be in a List
data class Muscles(val name: String, val image: Int, val id: Int)

fun getMuscles(): List<Muscles> {
    return listOf(
        Muscles("All equipment", R.drawable.all,3 ),
        Muscles("Shoulders", R.drawable.shoulders,2 ),
        Muscles("Biceps", R.drawable.biceps,1),
        Muscles("Hamstrings", R.drawable.hamstrings,11),
        Muscles("Calves", R.drawable.calves,7 ),
        Muscles("Glutes", R.drawable.glutes,8 ),
        Muscles("Lats", R.drawable.lats,12 ),
        Muscles("Chest", R.drawable.chest,4 ),
        Muscles("Quads", R.drawable.quadriceps,10 ),
        Muscles("Abs", R.drawable.abs,6 ),
        Muscles("Triceps", R.drawable.triceps,5 )
    )
}