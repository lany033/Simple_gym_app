package com.lifebetter.simplegymapp.ui.screens.exercises

import com.lifebetter.simplegymapp.R

//exercises must be in a List
data class Muscle(val name: String, val image: Int, val id: Int)

fun getMuscles(): List<Muscle> {
    return listOf(
        Muscle("All equipment", R.drawable.all,3 ),
        Muscle("Shoulders", R.drawable.shoulders,2 ),
        Muscle("Biceps", R.drawable.biceps,1),
        Muscle("Hamstrings", R.drawable.hamstrings,11),
        Muscle("Calves", R.drawable.calves,7 ),
        Muscle("Glutes", R.drawable.glutes,8 ),
        Muscle("Lats", R.drawable.lats,12 ),
        Muscle("Chest", R.drawable.chest,4 ),
        Muscle("Quads", R.drawable.quadriceps,10 ),
        Muscle("Abs", R.drawable.abs,6 ),
        Muscle("Triceps", R.drawable.triceps,5 )
    )
}