package com.lifebetter.simplegymapp.ui

interface Paginator<Key, Exercise> {
    suspend fun loadNextItems()
    fun reset()
}