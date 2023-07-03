package me.lulu.cleancodecasestudy.codecast

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.LicenseType
import me.lulu.cleancodecasestudy.User
import me.lulu.cleancodecasestudy.license.FindAllLicensesForUser
import java.text.SimpleDateFormat

class PresentCodecastUseCase(
    private val findAllCodecasts: FindAllCodecasts,
    private val findAllLicensesForUser: FindAllLicensesForUser
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun listCodecastsByUser(user: User): List<PresentableCodecast> {
        val allLicenses = findAllLicensesForUser.findAllLicensesForUser(user)

        return findAllCodecasts.findAllCodecasts()
            .map { codecast ->
                PresentableCodecast(
                    title = codecast.title,
                    publicationDate = dateFormat.format(codecast.publicationDate),
                    viewable = allLicenses.any { it.codecast == codecast && it.types.contains(LicenseType.VIEWABLE) }
                )
            }
    }

    fun isLicensedToCodecast(user: User, codecast: Codecast, flag: LicenseType): Boolean {
        return findAllLicensesForUser.findAllLicensesForUser(user).any {
            it.codecast == codecast && it.types.contains(flag)
        }
    }

}
