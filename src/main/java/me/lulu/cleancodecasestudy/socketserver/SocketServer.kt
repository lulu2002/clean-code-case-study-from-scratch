package me.lulu.cleancodecasestudy.socketserver

import kotlinx.coroutines.*
import java.net.ServerSocket

class SocketServer(val port: Int, private val service: SocketService) {
    private var running = false
    private var job: Job? = null
    private var socketInstance: ServerSocket = ServerSocket(port)

    suspend fun start() {
        job = CoroutineScope(Dispatchers.IO).async {
            running = true
            while (running) {
                val socket = socketInstance.accept()
                service.serve(socket)
            }
        }

        while (!running) {
            delay(1)
        }
    }

    suspend fun stop() {
        running = false
        socketInstance.close()
        job?.cancelAndJoin()
    }

    fun isRunning(): Boolean = running
}