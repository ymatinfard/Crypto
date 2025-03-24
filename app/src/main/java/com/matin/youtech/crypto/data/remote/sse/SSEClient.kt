package com.matin.youtech.crypto.data.remote.sse

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

interface SSEClient {
    fun listen(url: String): Flow<String>
}

class SSEClientImpl(private val client: OkHttpClient) : SSEClient {
    private var eventSource: EventSource? = null

    override fun listen(url: String): Flow<String> {
        val request = Request.Builder()
            .url(url)
            .build()
        return callbackFlow {
            val listener = object : EventSourceListener() {
                override fun onEvent(
                    eventSource: EventSource,
                    id: String?,
                    type: String?,
                    data: String
                ) {
                    trySend(data)
                }

                override fun onFailure(
                    eventSource: EventSource,
                    t: Throwable?,
                    response: Response?
                ) {
                    close(t)
                }

                override fun onClosed(eventSource: EventSource) {
                    close()
                }
            }

            eventSource = EventSources.createFactory(client).newEventSource(request, listener)

            awaitClose {
                close()
            }
        }
    }
}