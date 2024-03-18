package com.lifebetter.simplegymapp.model.database

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lifebetter.simplegymapp.domain.Exercise
import com.lifebetter.simplegymapp.ui.screens.workout.LogWorkoutViewModel.SetValueState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @TypeConverter
    fun fromSetWorkout(setWorkout: List<SetWorkout>): String{
        return Gson().toJson(setWorkout)
    }
    @TypeConverter
    fun toSetWorkout(json: String): List<SetWorkout>{
        val type = object : TypeToken<List<SetWorkout>>() {}.type
        return  Gson().fromJson(json,type)
    }

    @TypeConverter
    fun fromUri(uri: List<String>): String{
        return Gson().toJson(uri)
    }

    @TypeConverter
    fun toUri(json: String): List<String>{
        val type = object : TypeToken<List<String>>() {}.type
        return  Gson().fromJson(json,type)
    }

    @TypeConverter
    fun fromTimestamp(value: String): LocalDateTime {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }



    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime): String {
        return date.toString()
    }
}