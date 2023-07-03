package me.lulu.cleancodecasestudy.db

import me.lulu.cleancodecasestudy.License
import me.lulu.cleancodecasestudy.license.LicenseGateway
import me.lulu.cleancodecasestudy.User

class LicenseGatewayInMemory : LicenseGateway {
    private val licenses = mutableListOf<License>()

    override fun saveLicense(license: License) {
        licenses.add(license)
    }

    override fun findAllLicensesForUser(user: User): List<License> {
        return licenses.filter { it.user == user }.map { it.copy() }
    }
}