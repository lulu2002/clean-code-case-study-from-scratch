package me.lulu.cleancodecasestudy.doubles

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.codecast.FindAllCodecasts

class FindAllCodecastsStub(private val value: List<Codecast>) : FindAllCodecasts {
    override fun findAllCodecasts(): List<Codecast> {
        return value.toList()
    }
}