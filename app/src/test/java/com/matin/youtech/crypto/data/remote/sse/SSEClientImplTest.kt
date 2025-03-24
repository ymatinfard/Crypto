package com.matin.youtech.crypto.data.remote.sse

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SSEClientImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var sseClient: SSEClientImpl

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val client = OkHttpClient()
        sseClient = SSEClientImpl(client)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `listen emits events from SSE server`() = runTest {
        val sseResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                "id: 1\ndata: Hello, World!\n\n" + "id: 2\ndata: Another Event\n\n"
            )
            .setHeader("Content-Type", "text/event-stream")
            .setSocketPolicy(SocketPolicy.KEEP_OPEN)

        mockWebServer.enqueue(sseResponse)
        val testUrl = mockWebServer.url("/sse").toString()

        sseClient.listen(testUrl).test {
            assertEquals("Hello, World!", awaitItem()) // First event
            assertEquals("Another Event", awaitItem()) // Second event
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `listen handles SSE server disconnection`() = runTest {
        val sseResponse = MockResponse()
            .setResponseCode(200)
            .setBody("id: 1\ndata: First Event\n\n")
            .setHeader("Content-Type", "text/event-stream")
            .setSocketPolicy(SocketPolicy.SHUTDOWN_SERVER_AFTER_RESPONSE)

        mockWebServer.enqueue(sseResponse)
        val testUrl = mockWebServer.url("/sse").toString()

        sseClient.listen(testUrl).test {
            assertEquals("First Event", awaitItem())
            awaitComplete() // Ensures the flow completes after disconnection
        }
    }
}
