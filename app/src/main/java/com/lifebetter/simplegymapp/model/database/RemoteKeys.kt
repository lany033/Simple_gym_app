package com.lifebetter.simplegymapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val exerciseID: Int,
    val prevKey: String?,
    val currentPage: String,
    val nextKey: String?
)
