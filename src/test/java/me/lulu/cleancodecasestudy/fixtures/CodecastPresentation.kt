package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.*

class CodecastPresentation {

    private val gateKeeper = GateKeeper()
    private val useCase = PresentCodecastUseCase()
    private val gateway = testContext.getInstance(Gateway::class.java)

    fun loginUser(username: String): Boolean {
        val user = gateway.findUserByName(username) ?: return false
        gateKeeper.loggedInUser = user
        return true
    }

    fun addUser(username: String): Boolean {
        gateway.saveUser(User(username))
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