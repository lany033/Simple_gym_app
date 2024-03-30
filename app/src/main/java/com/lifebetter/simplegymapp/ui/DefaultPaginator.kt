package com.lifebetter.simplegymapp.ui

class DefaultPaginator<Key, Exercise> (
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Exercise>>,
    private inline val getNextKey: suspend (List<Exercise>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend ( exercises: List<Exercise>, newKey: Key) -> Unit
): Paginator<Key, Exercise>{

    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextItems() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        val exercises = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(exercises)
        onSuccess(exercises,currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }

}