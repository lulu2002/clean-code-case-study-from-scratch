package me.lulu.cleancodecasestudy.codecast

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.doubles.FindAllCodecastsStub
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class FindAllCodecastsSortedByDateDecoratorTest {

    private lateinit var base: FindAllCodecasts
    private lateinit var decorator: FindAllCodecastsSortedByDateDecorator

    private lateinit var codecastOld: Codecast
    private lateinit var codecastBetween: Codecast
    private lateinit var codecastNew: Codecast

    @BeforeEach
    fun setup() {
        codecastOld = Codecast("old", Date(0))
        codecastBetween = Codecast("between", Date(1000))
        codecastNew = Codecast("new", Date(2000))

        base = FindAllCodecastsStub(listOf(codecastNew, codecastOld, codecastBetween))
    }


    @Test
    fun `should return sorted codecasts`() {
        decorator = FindAllCodecastsSortedByDateDecorator(base)

        val codecasts = decorator.findAllCodecasts()

        assertEquals(
            listOf(
                codecastOld,
                codecastBetween,
                codecastNew
            ), codecasts
        )
    }

}