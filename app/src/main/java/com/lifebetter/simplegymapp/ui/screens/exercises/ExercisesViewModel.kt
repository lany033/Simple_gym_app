package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifebetter.simplegymapp.data.Exercise
import com.lifebetter.simplegymapp.data.ExercisePagingSource
import com.lifebetter.simplegymapp.data.toExercise
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.lifebetter.simplegymapp.model.ExercisesRepository
import com.lifebetter.simplegymapp.ui.DefaultPaginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

<<<<<<< HEAD
class ExercisesViewModel(private val exercisesRepository: ExercisesRepository) : ViewModel() {

=======
class ExercisesViewModel(private val exercisesRepository: ExercisesRepository): ViewModel() {

    val exercisePager = Pager(PagingConfig(pageSize = 20)){
        ExercisePagingSource(exercisesRepository)
    }.flow.cachedIn(viewModelScope)


    /*
>>>>>>> main
    //view
    private var _state = MutableStateFlow(ExerciseState())
    var state: StateFlow<ExerciseState> = _state.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = _state.value.page,
        onLoadUpdated = {
            _state.value = _state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            exercisesRepository.getExercises(nextPage, 20)
                .mapCatching { e -> e.results.map { it.toExercise() } }
        },
        getNextKey = {
            _state.value.page + 20
        },
        onError = {
            _state.value = _state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { exercise, newKey ->
            _state.value = _state.value.copy(
                exercise = _state.value.exercise + exercise,
                page = newKey,
                endReached = exercise.isEmpty()
            )
        }
    )

    init {
        loadNextItem()
    }

    fun loadNextItem(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    data class ExerciseState(
        val isLoading: Boolean = false,
        val exercise: List<Exercise> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 0
    )

<<<<<<< HEAD
=======
     */
>>>>>>> main
}

