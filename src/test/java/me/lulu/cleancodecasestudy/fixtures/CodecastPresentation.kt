package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.*
import java.util.UUID

class CodecastPresentation {

    private val gateKeeper = GateKeeper()
    private val gateway = testContext.getInstance(Gateway::class.java)
    private val useCase = PresentCodecastUseCase(gateway)

    fun loginUser(username: String): Boolean {
        val user = gateway.findUserByName(username) ?: return false
        gateKeeper.loggedInUser = user
        return true
    }

    fun addUser(username: String): Boolean {
        gateway.saveUser(User(UUID.randomUUID().toString(), username))
        return true
    }

    fun createLicenseForViewing(userName: String, codecastName: String): Boolean {
        val user = gateway.findUserByName(userName) ?: return false
        val codecast = gateway.findCodecastByTitle(codecastName) ?: return false
        val license = License(user, codecast)

        gateway.saveLicense(license)

        return useCase.isLicensedToViewCodecast(user, codecast)
    }

    fun presentationUser(): String {
        return gateKeeper.loggedInUser?.name ?: ""
    }

    fun clearCodecasts(): Boolean {
        gateway.findAllCodecasts().forEach { gateway.deleteCodecast(it) }

        return gateway.findAllCodecasts().isEmpty()
    }

    fun countOfCodecastsPresented(): Int {
        return useCase.listCodecastsByUser(gateKeeper.loggedInUser!!).size
    }
}