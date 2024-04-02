package com.lifebetter.simplegymapp.usecases

import com.lifebetter.simplegymapp.data.ExercisesRepository
import com.lifebetter.simplegymapp.domain.Error
import javax.inject.Inject

class RequestPopularExerciseUseCase @Inject constructor(private val exercisesRepository: ExercisesRepository) {

    suspend operator fun invoke(): com.lifebetter.simplegymapp.domain.Error? {
        return exercisesRepository.requestExercises()
    }
}