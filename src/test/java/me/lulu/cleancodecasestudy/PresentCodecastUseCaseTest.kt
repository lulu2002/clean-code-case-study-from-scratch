package me.lulu.cleancodecasestudy

import me.lulu.cleancodecasestudy.codecast.CodecastGateway
import me.lulu.cleancodecasestudy.codecast.PresentCodecastUseCase
import me.lulu.cleancodecasestudy.codecast.PresentableCodecast
import me.lulu.cleancodecasestudy.doubles.CodecastGatewayInMemory
import me.lulu.cleancodecasestudy.doubles.LicenseGatewayInMemory
import me.lulu.cleancodecasestudy.license.LicenseGateway
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class PresentCodecastUseCaseTest {

    private lateinit var useCase: PresentCodecastUseCase
    private lateinit var licenseGateway: LicenseGateway
    private lateinit var codecastGateway: CodecastGateway
    private lateinit var user: User
    private lateinit var codecast: Codecast

    @BeforeEach
    fun setup() {
        licenseGateway = LicenseGatewayInMemory()
        codecastGateway = CodecastGatewayInMemory()
        useCase = PresentCodecastUseCase(codecastGateway, licenseGateway)

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
            licenseGateway.saveLicense(License(user, codecast, listOf(LicenseType.VIEWABLE)))
            assertTrue(useCase.isLicensedToCodecast(user, codecast, LicenseType.VIEWABLE))
        }

        @Test
        fun `should not be able to view others codecast`() {
            val anotherUser = User("anotherId", "user")
            licenseGateway.saveLicense(License(anotherUser, codecast, listOf(LicenseType.VIEWABLE)))

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
            codecastGateway.saveCodecast(codecast)

            assertEquals(
                listOf(presentableCodecast(false, false)),
                useCase.listCodecastsByUser(user)
            )
        }

        @Test
        fun `should return codecast with viewable`() {
            codecastGateway.saveCodecast(codecast)
            createSavedLicense(listOf(LicenseType.VIEWABLE))

            assertEquals(
                listOf(presentableCodecast(true, false)),
                useCase.listCodecastsByUser(user)
            )
        }

        @Test
        fun `should return codecast with downloadable`() {
            codecastGateway.saveCodecast(codecast)
            createSavedLicense(listOf(LicenseType.DOWNLOADABLE))

            assertEquals(
                listOf(presentableCodecast(false, true)),
                useCase.listCodecastsByUser(user)
            )
        }

        private fun createSavedLicense(flags: List<LicenseType>) =
            License(user, codecast, flags).also { licenseGateway.saveLicense(it) }

        private fun presentableCodecast(viewable: Boolean, downloadable: Boolean) = PresentableCodecast(
            title = codecast.title,
            publicationDate = "2022-12-20",
            viewable = viewable,
            downloadable = downloadable
        )

    }

}