package com.matin.youtech.crypto.data

import javax.inject.Inject

class DataFlowManagerFactory @Inject constructor() {
    fun <Params : Any, Domain : Any> create(
        fetchFromNetwork: suspend (Params) -> Domain,
        fetchFromMemory: (Params) -> Domain? = { null },
        saveToMemory: (Params, Domain) -> Unit = { _, _ -> },
        fetchFromStorage: suspend (Params) -> Domain? = { null },
        saveToStorage: suspend (Params, Domain) -> Unit = { _, _ -> }
    ): DataFlowManager<Params, Domain> {
        return DataFlowManager(
            fetchFromNetwork,
            fetchFromMemory,
            saveToMemory,
            fetchFromStorage,
            saveToStorage
        )
    }
}