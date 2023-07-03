package me.lulu.cleancodecasestudy.socket

import kotlinx.coroutines.runBlocking
import me.lulu.cleancodecasestudy.doubles.SocketServiceFake
import me.lulu.cleancodecasestudy.socketserver.SocketServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.Socket
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SocketServerTest {

    private lateinit var server: SocketServer
    private lateinit var service: SocketServiceFake
    private val port: Int = 8080

    @BeforeEach
    fun setup() {
        service = SocketServiceFake()
        server = SocketServer(port, service)
    }

    @AfterEach
    fun tearDown() = runBlocking {
        server.stop()
    }

    @Test
    fun `should be able to start and stop`() = runBlocking {
        server.start()
        assertTrue { server.isRunning() }

        server.stop()
        assertFalse { server.isRunning() }
    }

    @Test
    fun `should accept incoming connection`() = runBlocking {
        server.start()
        Socket("localhost", port)
        server.stop()

        assertEquals(1, service.connections)
    }


}