package me.lulu.cleancodecasestudy.codecast

import me.lulu.cleancodecasestudy.Codecast

class FindAllCodecastsSortedByDateDecorator(private val base: FindAllCodecasts) : FindAllCodecasts {
    override fun findAllCodecasts(): List<Codecast> {
        return base.findAllCodecasts().sortedBy { it.publicationDate }
    }
}