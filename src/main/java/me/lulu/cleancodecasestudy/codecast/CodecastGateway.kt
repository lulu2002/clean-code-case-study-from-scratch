package me.lulu.cleancodecasestudy.codecast

import me.lulu.cleancodecasestudy.Codecast

interface CodecastGateway : FindAllCodecasts {
    fun deleteCodecast(codecast: Codecast)
    fun saveCodecast(codecast: Codecast)
    fun findCodecastByTitle(name: String): Codecast?
}