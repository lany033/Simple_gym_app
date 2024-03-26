package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lifebetter.simplegymapp.model.remotedata.items.Equipment
import com.lifebetter.simplegymapp.model.remotedata.items.Language
import com.lifebetter.simplegymapp.model.remotedata.items.Muscle

@Entity
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Int = 0,
    val name: String,
    val description: String,
    val images: String,
    val muscles: List<Muscle>,
    val language: Language,
    val id: Int,
    val equipment: List<Equipment>
)