package com.lifebetter.simplegymapp.domain

import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel.SetValueState

data class SetValue(
    val setNumber: Int,
    val kg: Int,
    val rep: Int,
    val isChecked: Boolean
)

fun SetValueState.toSetValueDomain(): SetValue = SetValue(
    setNumber = setNumber,
    kg = kg,
    rep = rep,
    isChecked = isChecked
)