package com.lifebetter.simplegymapp.model.remotedata

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lifebetter.simplegymapp.model.database.ExerciseDatabase
import com.lifebetter.simplegymapp.model.mappers.toLocalModel
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class ExerciseRemoteMediator(
    private val exerciseDb: ExerciseDatabase,
    private val exerciseService: ExercisesRemoteDataSource

) : RemoteMediator<Int, RemoteKeys>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RemoteKeys>
    ): MediatorResult {
        return try {
            val pageKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next ?: "https://wger.de/api/v2/exercise/?limit=20&offset=0"
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with `endOfPaginationReached = false` because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                    // the end of pagination for prepend.
                    val prevKey = remoteKeys?.previous
                    if (prevKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    }
                    prevKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with `endOfPaginationReached = false` because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val nextKey = remoteKeys?.next
                    if (nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    }
                    nextKey
                }
            }
            //val exercises = exerciseService.getExercise(loadKey,state.config.pageSize)
            val loadKeyToInt =
                (pageKey.split("/".toRegex()).last().split("=".toRegex()).last()).toInt()
            val exercises = exerciseService.getExercises(state.config.pageSize, loadKeyToInt)
            val endOfPaginationReached = exercises.results.isEmpty()

            exerciseDb.withTransaction {
                /*
                if (loadType == LoadType.REFRESH){ exerciseDb.dao.clearAll()}
                val exercisesEntities = exercises.results.toLocalModel()
                exerciseDb.dao.upsertAll(exercisesEntities)
                 */
                if (loadType == LoadType.REFRESH) {
                    exerciseDb.keydao.clearRemoteKeys()
                    exerciseDb.dao.clearAll()
                }
                val count = exercises.count
                //val prevKey = if (loadKeyToInt == 0)  null else loadKeyToInt - 20
                //val prevKey = if (pageKey == "https://wger.de/api/v2/exercise/?limit=20&offset=0") null else loadKeyToInt - 20
                val prevKey =
                    if (pageKey == "https://wger.de/api/v2/exercise/?limit=20&offset=0") null
                    else "https://wger.de/api/v2/exercise/?limit=20&offset=${loadKeyToInt - 20}"
                //val nextKey = if (endOfPaginationReached) null else loadKeyToInt + 20
                val nextKey =
                    if (endOfPaginationReached) null
                    else "https://wger.de/api/v2/exercise/?limit=20&offset=${loadKeyToInt + 20}"
                val keys = exercises.results.map {
                    RemoteKeys(
                        id = it.id,
                        previous = prevKey,
                        next = nextKey,
                        count = count
                    )
                }
                exerciseDb.keydao.insertAll(keys)
                exerciseDb.dao.upsertAll(exercises.results.toLocalModel())
            }
            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RemoteKeys>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                exerciseDb.keydao.remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RemoteKeys>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                exerciseDb.keydao.remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RemoteKeys>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                exerciseDb.keydao.remoteKeysRepoId(repoId)
            }
        }
    }
}