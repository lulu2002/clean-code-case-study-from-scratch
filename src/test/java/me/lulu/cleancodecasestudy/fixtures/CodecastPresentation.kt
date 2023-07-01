package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.Context
import me.lulu.cleancodecasestudy.GateKeeper
import me.lulu.cleancodecasestudy.GatewayMock
import me.lulu.cleancodecasestudy.User

class CodecastPresentation {

    private val gateKeeper = GateKeeper()
    private val useCase = PresentCodecastUseCase()

    init {
        Context.gateway = GatewayMock()
    }

    fun loginUser(username: String): Boolean {
        val user = Context.gateway.findUserByName(username) ?: return false
        gateKeeper.loggedInUser = user
        return true
    }

    fun addUser(username: String): Boolean {
        Context.gateway.saveUser(User(username))
        return true
    }

    fun createLicenseForViewing(user: String, codecast: String): Boolean {
        return false
    }

    fun presentationUser(): String {
        return gateKeeper.loggedInUser?.name ?: ""
    }

    fun clearCodecasts(): Boolean {
        val gateway = Context.gateway
        gateway.findAllCodecasts().forEach { gateway.deleteCodecast(it) }

        return gateway.findAllCodecasts().isEmpty()
    }

    fun countOfCodecastsPresented(): Int {
        return useCase.listCodecastsByUser(gateKeeper.loggedInUser!!).size
    }
}