package com.lifebetter.simplegymapp.model.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lifebetter.simplegymapp.domain.Exercise

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
}