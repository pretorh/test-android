package com.pretorh.myapplication

import android.app.Application
import com.pretorh.myapplication.di.DaggerInjector
import com.pretorh.myapplication.di.DefaultModule
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.di.PersistenceModule

class MyApplication : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        injector = buildDependencyInjector()
    }

    private fun buildDependencyInjector(): Injector {
        return DaggerInjector.builder()
            .defaultModule(DefaultModule())
            .persistenceModule(PersistenceModule.build(this))
            .build()
    }
}
