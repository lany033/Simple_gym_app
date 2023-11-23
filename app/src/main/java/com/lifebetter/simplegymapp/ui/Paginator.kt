package com.lifebetter.simplegymapp.ui

interface Paginator<Key, ExerciseResult> {
    suspend fun loadNextItems()
    fun reset()
}