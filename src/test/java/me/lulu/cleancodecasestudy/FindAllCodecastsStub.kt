package me.lulu.cleancodecasestudy

import me.lulu.cleancodecasestudy.codecast.FindAllCodecasts

class FindAllCodecastsStub(private val value: List<Codecast>) : FindAllCodecasts {
    override fun findAllCodecasts(): List<Codecast> {
        return value.toList()
    }
}