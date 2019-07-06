package com.pretorh.myapplication

import android.app.Application
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnitRunner

class TestRunner : AndroidJUnitRunner() {
    private lateinit var application: MyApplication
    private lateinit var persistenceBuilder: InMemoryPersistenceBuilder

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        application = super.newApplication(cl, className, context) as MyApplication
        persistenceBuilder = InMemoryPersistenceBuilder(application)
        return application
    }

    override fun onStart() {
        application.buildDependencyInjector(persistenceBuilder.module(), TestAsyncTaskTracker())
        super.onStart()
    }

    companion object {
        fun get() = InstrumentationRegistry.getInstrumentation() as TestRunner
    }
}
