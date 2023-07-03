package me.lulu.cleancodecasestudy

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `equals() should return state base on user id`() {
        val user1 = User("id1", "user1")
        val user2 = User("id1", "user2")
        val user3 = User("id2", "user1")

        assertEquals(user1, user2)
        assertNotEquals(user1, user3)
    }

}