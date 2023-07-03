package me.lulu.cleancodecasestudy.cucumber

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Stage
import io.cucumber.guice.CucumberModules
import io.cucumber.guice.InjectorSource

class CucumberInjectSource : InjectorSource {

    override fun getInjector(): Injector {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(), TestModule())
    }

}