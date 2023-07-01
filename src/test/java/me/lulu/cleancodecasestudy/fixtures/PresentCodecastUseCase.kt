package me.lulu.cleancodecasestudy.fixtures

import me.lulu.cleancodecasestudy.Codecast
import me.lulu.cleancodecasestudy.PresentableCodecast
import me.lulu.cleancodecasestudy.User

class PresentCodecastUseCase {

    fun listCodecastsByUser(user: User): List<PresentableCodecast> {
        return emptyList()
    }

    fun isLicensedToViewCodecast(user: User, codecast: Codecast): Boolean {
        TODO("Not yet implemented")
    }

}
