package me.lulu.cleancodecasestudy.db

import me.lulu.cleancodecasestudy.User
import me.lulu.cleancodecasestudy.user.UserGateway

class UserGatewayInMemory : UserGateway {
    private val users = mutableListOf<User>()

    override fun saveUser(user: User) {
        users.add(user)
    }

    override fun findUserByName(username: String): User? {
        return users.find { it.name == username }?.copy()
    }
}