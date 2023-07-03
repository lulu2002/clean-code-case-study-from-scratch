package me.lulu.cleancodecasestudy.user

import me.lulu.cleancodecasestudy.User

interface UserGateway {
    fun saveUser(user: User)
    fun findUserByName(username: String): User?
}