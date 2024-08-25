package com.matin.youtech.crypto.data.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

data class Data<T>(val content: T? = null, val error: Throwable? = null, val loading: Boolean = false)

class DataFlowManager<Params : Any, Domain : Any> @Inject constructor(
    private val fetchFromNetwork: suspend (Params) -> Domain,
    private val fetchFromMemory: (Params) -> Domain? = { null },
    private val saveToMemory: (Params, Domain) -> Unit = { _, _ -> },
    private val fetchFromStorage: suspend (Params) -> Domain? = { null },
    private val saveToStorage: suspend (Params, Domain) -> Unit = { _, _ -> }
) {

    private val sharedFlows = ConcurrentHashMap<Params, MutableSharedFlow<Data<Domain>>>()

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun observe(params: Params, forceReload: Boolean): Flow<Data<Domain>> = flow {
        val cachedData = fetchFromMemory(params)
        val shouldLoad = cachedData == null || forceReload
        emitAll(
            fetchFromStorage(params, cachedData)
                .flatMapMerge { storageData ->
                    fetchFromNetworkIfNeeded(shouldLoad, storageData, params)
                }
                .onStart {
                    emit(Data(content = cachedData, loading = shouldLoad))
                }
                .distinctUntilChanged()
        )
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private suspend fun fetchFromNetworkIfNeeded(
        shouldLoad: Boolean,
        storageData: Data<Domain>,
        params: Params
    ): Flow<Data<Domain>> {
        val sharedFlow = getSharedFlow(params)
        return if (shouldLoad) {
            val loadingData = storageData.copy(loading = true)
            sharedFlow.emit(loadingData)
            flowOf(loadingData)
                .onCompletion {
                    if (it == null) emitAll(
                        merge(
                            fetchFromNetwork(params, storageData.content),
                            sharedFlow
                        )
                    )
                }
        } else {
            flowOf(storageData).onCompletion { if (it == null) emitAll(sharedFlow) }
        }
    }

    private suspend fun fetchFromStorage(
        params: Params,
        cachedData: Domain?
    ): Flow<Data<Domain>> =
        if (cachedData != null) {
            flowOf(Data(cachedData))
        } else {
            flow {
                val storageData = fetchFromStorage(params)
                storageData?.let {
                    saveToMemory(params, it)
                }
                emit(Data(content = storageData))
            }.flowOn(DataFlowManagerDispatchers.ioDispatcher)
                .catch { throwable ->
                    val loadingErrorData = Data<Domain>(error = throwable, loading = true)
                    emit(loadingErrorData)
                    emitAll(getSharedFlow(params))
                }
        }

    private suspend fun fetchFromNetwork(
        params: Params,
        cachedData: Domain?
    ): Flow<Data<Domain>> {
        val sharedFlow = getSharedFlow(params)
        return flow {
            val networkData = fetchFromNetwork(params)
            saveToMemory(params, networkData)
            saveToStorage(params, networkData)
            emit(Data(content = networkData))
        }.flowOn(DataFlowManagerDispatchers.ioDispatcher)
            .catch { error ->
                val errorData = Data(content = cachedData, error = error)
                emit(errorData)
                sharedFlow.emit(errorData)
                emitAll(sharedFlow)
            }
    }

    private fun getSharedFlow(params: Params): MutableSharedFlow<Data<Domain>> =
        sharedFlows.getOrPut(params) { MutableSharedFlow(replay = 1) }

    object DataFlowManagerDispatchers {
        val ioDispatcher = Dispatchers.IO
    }
}
