package me.lulu.cleancodecasestudy

class GatewayMock : Gateway {

    private val codecasts = mutableListOf<Codecast>()
    private val users = mutableListOf<User>()

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
        return users.find { it.name == username }
    }

    override fun findCodecastByTitle(name: String): Codecast? {
        TODO("Not yet implemented")
    }

    override fun saveLicense(license: License) {
        TODO("Not yet implemented")
    }
}