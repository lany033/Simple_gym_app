package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.model.remotedata.items.Language


@Entity
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String

)


@Entity
data class Language(
    @PrimaryKey(autoGenerate = false)
    val id:Int
)