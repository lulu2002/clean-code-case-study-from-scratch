package me.lulu.cleancodecasestudy.fixtures

import java.util.*

//OrderedQuery
class OfCodeCasts {
    private fun list(vararg objects: Any): List<Any> {
        return Arrays.asList(*objects)
    }

    fun query(): List<Any> {
        return list(
            list()
        )
    }
}