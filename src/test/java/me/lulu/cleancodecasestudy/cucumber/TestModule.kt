package me.lulu.cleancodecasestudy.cucumber

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import me.lulu.cleancodecasestudy.license.LicenseGateway
import me.lulu.cleancodecasestudy.codecast.CodecastGateway
import me.lulu.cleancodecasestudy.codecast.FindAllCodecasts
import me.lulu.cleancodecasestudy.codecast.FindAllCodecastsSortedByDateDecorator
import me.lulu.cleancodecasestudy.codecast.PresentCodecastUseCase
import me.lulu.cleancodecasestudy.db.CodecastGatewayInMemory
import me.lulu.cleancodecasestudy.db.LicenseGatewayInMemory
import me.lulu.cleancodecasestudy.db.UserGatewayInMemory
import me.lulu.cleancodecasestudy.license.FindAllLicensesForUser
import me.lulu.cleancodecasestudy.user.UserGateway


class TestModule : AbstractModule() {


    @Provides
    @Singleton
    fun licenseGateway(): LicenseGateway {
        return LicenseGatewayInMemory()
    }

    @Provides
    @Singleton
    fun userGateway(): UserGateway {
        return UserGatewayInMemory()
    }

    @Provides
    @Singleton
    fun codecastGateway(): CodecastGateway {
        return CodecastGatewayInMemory()
    }

    @Provides
    @Singleton
    fun findAllCodecasts(codecastGateway: CodecastGateway): FindAllCodecasts {
        return FindAllCodecastsSortedByDateDecorator(codecastGateway)
    }

    @Provides
    @Singleton
    fun findAllLicensesForUser(licenseGateway: LicenseGateway): FindAllLicensesForUser {
        return licenseGateway
    }

    @Provides
    @Singleton
    fun useCase(
        findAllCodecasts: FindAllCodecasts,
        findAllLicensesForUser: FindAllLicensesForUser
    ): PresentCodecastUseCase {
        return PresentCodecastUseCase(
            findAllCodecasts,
            findAllLicensesForUser
        )
    }
}
