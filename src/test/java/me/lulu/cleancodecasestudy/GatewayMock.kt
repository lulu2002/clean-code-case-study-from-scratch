package me.lulu.cleancodecasestudy

class GatewayMock : Gateway {

    private val codecasts = mutableListOf<Codecast>()
    private val users = mutableListOf<User>()
    private val licenses = mutableListOf<License>()

    override fun findAllCodecasts(): List<Codecast> {
        return codecasts.toList()
    }

    override fun deleteCodecast(codecast: Codecast) {
        codecasts.remove(codecast)
    }

    override fun saveCodecast(codecast: Codecast) {
        codecasts.add(codecast)
    }

    override fun saveUser(user: User) {
        users.add(user)
    }

    override fun findUserByName(username: String): User? {
        return users.find { it.name == username }?.copy()
    }

    override fun findCodecastByTitle(name: String): Codecast? {
        return codecasts.find { it.title == name }?.copy()
    }

    override fun saveLicense(license: License) {
        licenses.add(license)
    }

    override fun findAllLicensesForUser(user: User): List<License> {
        return licenses.filter { it.user == user }.map { it.copy() }
    }
}