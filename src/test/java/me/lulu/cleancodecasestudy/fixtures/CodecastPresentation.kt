package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.Context
import me.lulu.cleancodecasestudy.GatewayMock
import me.lulu.cleancodecasestudy.User

class CodecastPresentation {

    init {
        Context.gateway = GatewayMock()
    }

    fun loginUser(username: String): Boolean {
        return false
    }

    fun addUser(username: String): Boolean {
        Context.gateway.saveUser(User(username))
        return true
    }

    fun createLicenseForViewing(user: String, codecast: String): Boolean {
        return false
    }

    fun presentationUser(): String {
        return "TILT"
    }

    fun clearCodecasts(): Boolean {
        val gateway = Context.gateway
        gateway.findAllCodecasts().forEach { gateway.deleteCodecast(it) }

        return gateway.findAllCodecasts().isEmpty()
    }

    fun countOfCodecastsPresented(): Int {
        return -1
    }
}