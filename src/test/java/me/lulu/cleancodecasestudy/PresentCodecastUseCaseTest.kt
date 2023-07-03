package me.lulu.cleancodecasestudy

import me.lulu.cleancodecasestudy.codecast.PresentCodecastUseCase
import me.lulu.cleancodecasestudy.codecast.PresentableCodecast
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class PresentCodecastUseCaseTest {

    private lateinit var useCase: PresentCodecastUseCase
    private lateinit var gateway: Gateway
    private lateinit var user: User
    private lateinit var codecast: Codecast

    @BeforeEach
    fun setup() {
        gateway = GatewayMock()
        useCase = PresentCodecastUseCase(gateway, gateway)

        user = User("id", "user")
        codecast = Codecast("title", SimpleDateFormat("yyyy-MM-dd").parse("2022-12-20"))
    }

    @Nested
    inner class IsLicensedToCodecast {

        @Test
        fun `should return false if user without license`() {
            assertFalse(useCase.isLicensedToCodecast(user, codecast, LicenseType.VIEWABLE))
        }

        @Test
        fun `should return true if user is licensed`() {
            gateway.saveLicense(License(user, codecast, listOf(LicenseType.VIEWABLE)))
            assertTrue(useCase.isLicensedToCodecast(user, codecast, LicenseType.VIEWABLE))
        }

        @Test
        fun `should not be able to view others codecast`() {
            val anotherUser = User("anotherId", "user")
            gateway.saveLicense(License(anotherUser, codecast, listOf(LicenseType.VIEWABLE)))

            assertFalse(useCase.isLicensedToCodecast(user, codecast, LicenseType.VIEWABLE))
        }

    }


    @Nested
    inner class ListCodecastsByUser {

        @Test
        fun `should return empty if user has no codecasts`() {
            assertTrue(useCase.listCodecastsByUser(user).isEmpty())
        }

        @Test
        fun `should return codecast but not viewable`() {
            gateway.saveCodecast(codecast)

            assertEquals(
                listOf(presentableCodecast(false)),
                useCase.listCodecastsByUser(user)
            )
        }

        @Test
        fun `should return codecast with viewable`() {
            gateway.saveCodecast(codecast)
            gateway.saveLicense(License(user, codecast, listOf(LicenseType.VIEWABLE)))

            assertEquals(
                listOf(presentableCodecast(true)),
                useCase.listCodecastsByUser(user)
            )
        }

        private fun presentableCodecast(viewable: Boolean) = PresentableCodecast(
            title = codecast.title,
            publicationDate = "2022-12-20",
            viewable = viewable
        )

    }

}