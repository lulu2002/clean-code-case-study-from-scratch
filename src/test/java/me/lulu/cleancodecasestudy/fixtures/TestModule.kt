package me.lulu.cleancodecasestudy.fixtures

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Provides
import com.google.inject.Singleton
import me.lulu.cleancodecasestudy.Gateway
import me.lulu.cleancodecasestudy.GatewayMock


class TestModule : AbstractModule() {


    @Provides
    @Singleton
    fun gateWay(): Gateway {
        return GatewayMock()
    }

//    @Inject
//    @Singleton


}

val testContext = Guice.createInjector(
    TestModule()
)
