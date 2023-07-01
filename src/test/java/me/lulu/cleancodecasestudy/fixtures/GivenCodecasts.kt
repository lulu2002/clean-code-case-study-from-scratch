package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.Context

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
        Context.gateway.saveCodecast(codecast)
    }
}