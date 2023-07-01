package me.lulu.cleancodecasestudy

interface Gateway {
    fun findAllCodecasts(): List<Codecast>
    fun deleteCodecast(codecast: Codecast)
    fun saveCodecast(codecast: Codecast)
    fun saveUser(user: User)
    fun findUserByName(username: String): User?
    fun findCodecastByTitle(name: String): Codecast?
    fun saveLicense(license: License)
}