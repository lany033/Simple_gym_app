package com.lifebetter.simplegymapp.ui.screens.exercises

import com.lifebetter.simplegymapp.R

//exercises must be in a List
data class Equipment(val name: String, val image: Int, val id: Int)

fun getEquipments(): List<Equipment> {
    return listOf(
        Equipment("All equipment", R.drawable.all,11 ),
        Equipment("Bodyweight exercise", R.drawable.bodyweight,7 ),
        Equipment("Barbell", R.drawable.barbell,1),
        Equipment("Bench", R.drawable.bench,8),
        Equipment("Dumbbell", R.drawable.dumbbell,3 ),
        Equipment("Gym mat", R.drawable.gym_mat,4 ),
        Equipment("Incline bench", R.drawable.incline_bench,9 ),
        Equipment("Kettlebell", R.drawable.kettlebell,10 ),
        Equipment("Pull-up bar", R.drawable.pullup_bar,6 ),
        Equipment("SZ-Bar", R.drawable.sz_bar,2 ),
        Equipment("Swiss Ball", R.drawable.swiss_ball,5 )
    )
}

