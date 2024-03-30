package com.lifebetter.simplegymapp.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel.SetValueState

class Converters {
    @TypeConverter
    fun fromExerciseList(exercises: List<Exercise>): String{
            return Gson().toJson(exercises)
    }
    @TypeConverter
    fun toExerciseList(json: String): List<Exercise>{
        val type = object : TypeToken<List<Exercise>>() {}.type
        return  Gson().fromJson(json,type)
    }

    @TypeConverter
    fun fromSetList(setValueState: List<SetValueState>): String{
        return Gson().toJson(setValueState)
    }
    @TypeConverter
    fun toSetList(json: String): List<SetValueState>{
        val type = object : TypeToken<List<SetValueState>>() {}.type
        return  Gson().fromJson(json,type)
    }
}