package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel.SetValueState

@Entity
data class SetWorkout(
    @PrimaryKey(autoGenerate = true)
    val setId:Int = 0,
    val exerciseName: String,
    val exerciseId: Int,
    val exerciseImage: String,
    val listSet: List<SetValueState>
)
