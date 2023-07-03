package me.lulu.cleancodecasestudy.doubles

import me.lulu.cleancodecasestudy.socketserver.SocketService
import java.net.Socket

class SocketServiceFake : SocketService {
    var connections: Int = 0

    override fun serve(socket: Socket) {
        connections++
        socket.close()
    }

}