package com.lifebetter.simplegymapp.ui.screens.exercises

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import com.lifebetter.simplegymapp.model.remotedata.ExerciseRemoteMediator
import com.lifebetter.simplegymapp.model.remotedata.ExerciseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PAGE_SIZE = 20

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val exerciseDb: ExerciseDatabase,
    private val exerciseService: ExerciseService
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getExercisesFromPaging(): Flow<PagingData<ExerciseEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {exerciseDb.dao().pagingSource()},
            remoteMediator = ExerciseRemoteMediator(exerciseDb, exerciseService)
        ).flow

    // val exercisePagingFlow = pager.flow.map {
    // pagingData -> pagingData.map { it } }.cachedIn(viewModelScope)
}

