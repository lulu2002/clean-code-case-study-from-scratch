package me.lulu.cleancodecasestudy.doubles

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.codecast.CodecastGateway

class CodecastGatewayInMemory : CodecastGateway {
    private val codecasts = mutableListOf<Codecast>()

    override fun findAllCodecasts(): List<Codecast> {
        return codecasts.toList()
    }

    override fun deleteCodecast(codecast: Codecast) {
        codecasts.remove(codecast)
    }

    override fun saveCodecast(codecast: Codecast) {
        codecasts.add(codecast)
    }


    override fun findCodecastByTitle(name: String): Codecast? {
        return codecasts.find { it.title == name }?.copy()
    }
}