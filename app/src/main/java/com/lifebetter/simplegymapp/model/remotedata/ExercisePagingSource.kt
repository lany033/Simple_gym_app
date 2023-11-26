package com.lifebetter.simplegymapp.model.remotedata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lifebetter.simplegymapp.model.ExercisesRepository

class ExercisePagingSource(
    private val repository: ExercisesRepository
): PagingSource<Int, ExerciseDto>() {
    //key -> page number
    //getRefreshKey() -> Puede retornar la pagina q es requerida
    override fun getRefreshKey(state: PagingState<Int, ExerciseDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1)?: page?.nextKey?.plus(1)
        }
    }

    //load() -> funcion principal que carga la data de la pagina
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExerciseDto> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = repository.getExercises(20, nextPageNumber)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if(response.results.isNotEmpty()) response.toPage() else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}