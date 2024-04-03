package com.lifebetter.simplegymapp.usecases

import com.lifebetter.simplegymapp.data.ExercisesRepository
import javax.inject.Inject

class GetPopularExercisesUseCase @Inject constructor(private val exercisesRepository: ExercisesRepository)  {

    operator fun invoke() = exercisesRepository.exercises

}