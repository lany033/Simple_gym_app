package com.lifebetter.simplegymapp

import android.app.Application
import androidx.room.Room
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase

class App : Application() {

    lateinit var db: ExerciseDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, ExerciseDatabase::class.java, "exercise-db").build()
    }

}