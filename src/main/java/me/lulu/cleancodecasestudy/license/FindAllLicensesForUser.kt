package me.lulu.cleancodecasestudy.license

import me.lulu.cleancodecasestudy.License
import me.lulu.cleancodecasestudy.User

interface FindAllLicensesForUser {
    fun findAllLicensesForUser(user: User): List<License>
}