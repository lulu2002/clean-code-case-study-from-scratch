package me.lulu.cleancodecasestudy

import me.lulu.cleancodecasestudy.codecast.FindAllCodecasts
import me.lulu.cleancodecasestudy.license.FindAllLicensesForUser

interface Gateway : FindAllCodecasts, FindAllLicensesForUser {
    override fun findAllCodecasts(): List<Codecast>
    fun deleteCodecast(codecast: Codecast)
    fun saveCodecast(codecast: Codecast)
    fun saveUser(user: User)
    fun findUserByName(username: String): User?
    fun findCodecastByTitle(name: String): Codecast?
    fun saveLicense(license: License)
    override fun findAllLicensesForUser(user: User): List<License>
}