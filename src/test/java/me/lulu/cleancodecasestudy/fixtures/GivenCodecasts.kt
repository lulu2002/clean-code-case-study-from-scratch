package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.Gateway

class GivenCodecasts {
    private var title: String = ""
    private var publicationDate: String = ""

    fun setTitle(title: String) {
        this.title = title
    }

    fun setPublished(publicationDate: String) {
        this.publicationDate = publicationDate
    }

    fun execute() {
        val codecast = Codecast(title, publicationDate)
        testContext.getInstance(Gateway::class.java).saveCodecast(codecast)
    }
}