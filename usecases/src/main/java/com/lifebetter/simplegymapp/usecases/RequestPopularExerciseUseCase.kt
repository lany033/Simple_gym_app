package com.lifebetter.simplegymapp.usecases

import com.lifebetter.simplegymapp.data.ExercisesRepository
import javax.inject.Inject
import com.lifebetter.simplegymapp.domain.Error

class RequestPopularExerciseUseCase @Inject constructor(private val exercisesRepository: ExercisesRepository) {

    suspend operator fun invoke(): Error? {
        return exercisesRepository.requestExercises()
    }
}