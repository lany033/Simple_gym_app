package com.lifebetter.simplegymapp.model.remotedata

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.database.ExerciseEntity
import com.lifebetter.simplegymapp.model.database.RemoteKeys
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
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    Log.d("REFRESH", remoteKeys?.nextKey.orEmpty())
                    remoteKeys?.nextKey ?: "https://wger.de/api/v2/exercise/?limit=20&offset=0"

                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    Log.d("PREPEND", prevKey.orEmpty())
                    prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with endOfPaginationReached = false because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.

                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    Log.d("APPEND", nextKey.orEmpty())
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val loadKeyToInt = loadKey.split("/".toRegex()).last()

            Log.d("loadkey", loadKeyToInt)
            //val exercises = exerciseService.getExercises(state.config.pageSize, loadKeyToInt)
            val exercises = exerciseService.getExercise(loadKeyToInt)

            exerciseDb.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    exerciseDb.dao().clearAll()
                    exerciseDb.keydao().clearRemoteKeys()
                }
                //val prevKey = if (loadKeyToInt > 0) loadKeyToInt - 20 else null
                val prevKey = exercises.previous
                Log.d("prevkey", prevKey.orEmpty())
                //val nextKey = if (exercises.results.isEmpty()) null else "https://wger.de/api/v2/exercise/?limit=20&offset=${loadKeyToInt + 20}"
                val nextKey = exercises.next
                Log.d("nextKey", nextKey.orEmpty())

                val remoteKeys =
                    exercises.results.map {
                        RemoteKeys(
                            exerciseID = it.toLocalModel().id,
                            prevKey = prevKey,
                            currentPage = loadKey,
                            nextKey = nextKey
                        )
                    }

                val exercisesEntities = exercises.results.toLocalModel()
                exerciseDb.keydao().insertAll(remoteKeys)
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

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ExerciseEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                exerciseDb.keydao().getRemoteKeyByExerciseID(id)
            }
        }

    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ExerciseEntity>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { exercise ->
            exerciseDb.keydao().getRemoteKeyByExerciseID(exercise.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ExerciseEntity>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { exercise ->
            exerciseDb.keydao().getRemoteKeyByExerciseID(exercise.id)
        }
    }

}