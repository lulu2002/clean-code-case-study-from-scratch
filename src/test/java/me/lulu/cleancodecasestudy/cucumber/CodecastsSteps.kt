package me.lulu.cleancodecasestudy.cucumber

import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import me.lulu.cleancodecasestudy.*
import me.lulu.cleancodecasestudy.fixtures.testContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CodecastsSteps {

    private val gateKeeper = GateKeeper()
    private val gateway = testContext.getInstance(Gateway::class.java)
    private val useCase = PresentCodecastUseCase(gateway)
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy")

    @Given("codecasts:")
    fun codecasts(table: DataTable) {
        table.asMaps().forEach {
            val codecast = Codecast(it["title"]!!, dateFormat.parse(it["published"]!!))
            gateway.saveCodecast(codecast)
        }
    }

    @Given("no codecasts")
    fun noCodecasts() {
        gateway.findAllCodecasts().forEach { gateway.deleteCodecast(it) }
        assertTrue { gateway.findAllCodecasts().isEmpty() }
    }

    @Given("user {string}")
    fun user(username: String) {
        gateway.saveUser(User(UUID.randomUUID().toString(), username))
    }

    @And("user {string} logged in")
    fun withUserLoggedIn(username: String) {
        val user = gateway.findUserByName(username) ?: error("User not found")
        gateKeeper.loggedInUser = user
    }

    @And("license for {string} to view {string}")
    fun licenseForUserToViewCodecast(username: String, codecastTitle: String) {
        val user = gateway.findUserByName(username) ?: error("User not found")
        val codecast = gateway.findCodecastByTitle(codecastTitle) ?: error("Codecast not found")
        val license = License(user, codecast)

        gateway.saveLicense(license)
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
//            assertTrue { codecast.picture == it["picture"] }
//            assertTrue { codecast.description == it["description"] }
            assertTrue { codecast.viewable == (it["viewable"] == "+") }
//            assertTrue { codecast.downloadable == (it["downloadable"] == "+") }
        }
    }

}