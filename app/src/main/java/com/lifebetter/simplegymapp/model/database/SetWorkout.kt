package com.lifebetter.simplegymapp.model.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel.SetValueState
import java.util.Timer


data class SetWorkout(
    val exerciseName: String,
    val exerciseId: Int,
    val exerciseImage: String,
    val listSet: List<SetValueState>,
)
