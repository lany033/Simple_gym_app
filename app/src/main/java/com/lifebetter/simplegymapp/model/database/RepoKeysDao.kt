package com.lifebetter.simplegymapp.model.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lifebetter.simplegymapp.model.remotedata.RemoteKeys
@Dao
interface RepoKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE Id = :repoId")
    suspend fun remoteKeysRepoId(repoId: Int): RemoteKeys?

    @Query("SELECT * FROM remote_keys")
    fun pagingSource(): PagingSource<Int, RemoteKeys>

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}