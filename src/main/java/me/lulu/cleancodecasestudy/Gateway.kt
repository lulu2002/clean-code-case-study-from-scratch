package me.lulu.cleancodecasestudy

interface Gateway {
    fun findAllCodecasts(): List<Codecast>
    fun deleteCodecast(codecast: Codecast)
    fun saveCodecast(codecast: Codecast)
    fun saveUser(user: User)
}