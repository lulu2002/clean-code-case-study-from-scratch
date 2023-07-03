package me.lulu.cleancodecasestudy.cucumber

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import me.lulu.cleancodecasestudy.Gateway
import me.lulu.cleancodecasestudy.GatewayMock
import me.lulu.cleancodecasestudy.codecast.FindAllCodecasts
import me.lulu.cleancodecasestudy.codecast.FindAllCodecastsSortedByDateDecorator
import me.lulu.cleancodecasestudy.codecast.PresentCodecastUseCase
import me.lulu.cleancodecasestudy.license.FindAllLicensesForUser


class TestModule : AbstractModule() {


    @Provides
    @Singleton
    fun gateWay(): Gateway {
        return GatewayMock()
    }

    @Provides
    @Singleton
    fun findAllCodecasts(gateway: Gateway): FindAllCodecasts {
        return FindAllCodecastsSortedByDateDecorator(gateway)
    }

    @Provides
    @Singleton
    fun findAllLicensesForUser(gateway: Gateway): FindAllLicensesForUser {
        return gateway
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

val testContext = Guice.createInjector(
    TestModule()
)
