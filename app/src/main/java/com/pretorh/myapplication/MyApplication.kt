package com.pretorh.myapplication

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.pretorh.myapplication.core.AsyncTaskTracker
import com.pretorh.myapplication.di.DaggerInjector
import com.pretorh.myapplication.di.DefaultModule
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.di.PersistenceModule

class MyApplication : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        buildDependencyInjector()
    }

    private fun buildDependencyInjector() {
        val persistenceModule = PersistenceModule.build(this)
        buildDependencyInjector(persistenceModule, AsyncTaskTracker())
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun buildDependencyInjector(persistenceModule: PersistenceModule, asyncTaskTracker: AsyncTaskTracker) {
        injector = DaggerInjector.builder()
            .defaultModule(DefaultModule(asyncTaskTracker))
            .persistenceModule(persistenceModule)
            .build()
    }
}
