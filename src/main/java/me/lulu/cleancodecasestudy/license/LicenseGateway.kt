package me.lulu.cleancodecasestudy.license

import me.lulu.cleancodecasestudy.License

interface LicenseGateway : FindAllLicensesForUser {
    fun saveLicense(license: License)
}