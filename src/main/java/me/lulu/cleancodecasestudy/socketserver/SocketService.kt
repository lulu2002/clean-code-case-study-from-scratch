package me.lulu.cleancodecasestudy.socketserver

import java.net.Socket

interface SocketService {
    fun serve(socket: Socket)
}