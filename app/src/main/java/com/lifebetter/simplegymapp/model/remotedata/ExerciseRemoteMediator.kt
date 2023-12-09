package com.lifebetter.simplegymapp.model.remotedata

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.log
import androidx.room.withTransaction
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class ExerciseRemoteMediator(
    private val exerciseDb: ExerciseDatabase,
    private val exerciseService: ExerciseService

) : RemoteMediator<Int, ExerciseEntity>() {

    // PagingState, que contiene información sobre las páginas que se cargaron hasta el momento,
    // el índice al que se accedió de forma más reciente
    // y el objeto PagingConfig que usaste para inicializar el flujo de paginación
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ExerciseEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    0

                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with endOfPaginationReached = false because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val lastItem  = state.lastItemOrNull()

                    if (lastItem == null ){
                        0
                    } else {

                        (lastItem.id / state.config.pageSize) * 10
                        Log.d("APPEND", (lastItem.id / state.config.pageSize).toString())
                    }

                }
            }
            val exercises = exerciseService.getExercise(state.config.pageSize, loadKey)

            exerciseDb.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    exerciseDb.dao().clearAll()
                }

                val exercisesEntities = exercises.results.toLocalModel()
                exerciseDb.dao().insertAll(exercisesEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = exercises.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}