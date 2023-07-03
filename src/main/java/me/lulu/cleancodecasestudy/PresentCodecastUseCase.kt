package me.lulu.cleancodecasestudy

import java.text.SimpleDateFormat

class PresentCodecastUseCase(private val gateway: Gateway) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun listCodecastsByUser(user: User): List<PresentableCodecast> {
        val allLicenses = gateway.findAllLicensesForUser(user)

        return gateway.findAllCodecasts().map { codecast ->
            PresentableCodecast(
                title = codecast.title,
                publicationDate = dateFormat.format(codecast.publicationDate),
                viewable = allLicenses.any { it.codecast == codecast }
            )
        }
    }

    fun isLicensedToViewCodecast(user: User, codecast: Codecast): Boolean {
        return gateway.findAllLicensesForUser(user).any { it.codecast == codecast }
    }

}
