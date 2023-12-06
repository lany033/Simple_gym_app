package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import kotlinx.coroutines.flow.map

class ExercisesViewModel(pager: Pager<Int, ExerciseEntity>): ViewModel() {


    /*
    val exercisePager = Pager(PagingConfig(pageSize = 20)){
        ExerciseRemoteMediator(exercisesRemoteDataSource)
    }.flow.cachedIn(viewModelScope)

     */

    /*

    //view
    private val _state = MutableStateFlow(ExercisesState())
    val state: StateFlow<ExercisesState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = ExercisesState(exercises = exercisesRepository.getExercises().results.map {
                it.toExercise()
            })
        }
    }

    //interface
    data class ExercisesState(
        val exercises: List<Exercise> = emptyList()
    )
     */

    val exercisePagingFlow = pager.flow.map { pagingData -> pagingData.map { it } }.cachedIn(viewModelScope)

}

