package me.lulu.cleancodecasestudy.cucumber

import com.google.inject.Inject
import io.cucumber.datatable.DataTable
import io.cucumber.guice.ScenarioScoped
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import me.lulu.cleancodecasestudy.*
import me.lulu.cleancodecasestudy.codecast.CodecastGateway
import me.lulu.cleancodecasestudy.codecast.PresentCodecastUseCase
import me.lulu.cleancodecasestudy.license.LicenseGateway
import me.lulu.cleancodecasestudy.user.UserGateway
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ScenarioScoped
class CodecastsSteps @Inject constructor(
    private val useCase: PresentCodecastUseCase,
    private val licenseGateway: LicenseGateway,
    private val codecastGateway: CodecastGateway,
    private val userGateway: UserGateway,
) {

    private val gateKeeper = GateKeeper()
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy")

    @Given("codecasts:")
    fun codecasts(table: DataTable) {
        table.asMaps().forEach {
            val codecast = Codecast(it["title"]!!, dateFormat.parse(it["published"]!!))
            codecastGateway.saveCodecast(codecast)
        }
    }

    @Given("no codecasts")
    fun noCodecasts() {
        codecastGateway.findAllCodecasts().forEach { codecastGateway.deleteCodecast(it) }
        assertTrue { codecastGateway.findAllCodecasts().isEmpty() }
    }

    @Given("user {string}")
    fun user(username: String) {
        userGateway.saveUser(User(UUID.randomUUID().toString(), username))
    }

    @And("user {string} logged in")
    fun withUserLoggedIn(username: String) {
        val user = userGateway.findUserByName(username) ?: error("User not found")
        gateKeeper.loggedInUser = user
    }

    @And("license for {string} to view {string}")
    fun licenseForUserToViewCodecast(username: String, codecastTitle: String) {
        val user = userGateway.findUserByName(username) ?: error("User not found")
        val codecast = codecastGateway.findCodecastByTitle(codecastTitle) ?: error("Codecast not found")
        val license = License(user, codecast, listOf(LicenseType.VIEWABLE))

        licenseGateway.saveLicense(license)
    }

    @And("license for {string} to download {string}")
    fun licenseForUserToDownloadCodecast(username: String, codecastTitle: String) {
        createAndSaveLicense(username, codecastTitle, listOf(LicenseType.DOWNLOADABLE))
    }

    private fun createAndSaveLicense(username: String, codecastTitle: String, flags: List<LicenseType>) {
        val user = userGateway.findUserByName(username) ?: error("User not found")
        val codecast = codecastGateway.findCodecastByTitle(codecastTitle) ?: error("Codecast not found")
        val license = License(user, codecast, flags)

        licenseGateway.saveLicense(license)
    }


    @Then("there will be no codecasts presented for {string}")
    fun noCodecastPresented(username: String) {
        assertTrue { useCase.listCodecastsByUser(gateKeeper.loggedInUser!!).isEmpty() }
    }

    @Then("there will be codecasts presented for {string}:")
    fun shouldPresentedCodecasts(username: String, table: DataTable) {
        val codecasts = useCase.listCodecastsByUser(gateKeeper.loggedInUser!!)

        table.asMaps().forEachIndexed { index, it ->
            val codecast = codecasts.get(index)
            assertEquals(it["title"], codecast.title)
            assertEquals(it["publication date"], codecast.publicationDate)
//            assertTrue { codecast.picture == it["picture"] }
//            assertTrue { codecast.description == it["description"] }
            assertEquals(codecast.viewable, it["viewable"] == "+")
            assertEquals(codecast.downloadable, it["downloadable"] == "+")
        }
    }

}