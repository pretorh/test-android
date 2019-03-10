package com.pretorh.myapplication

import android.app.Application
import com.pretorh.myapplication.di.DaggerInjector
import com.pretorh.myapplication.di.DefaultModule
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.di.PersistenceModule
import com.pretorh.myapplication.persistence.DbBackupHelper
import javax.inject.Inject

class MyApplication : Application() {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var dbBackupHelper: DbBackupHelper

    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        injector = buildDependencyInjector()
        injector.inject(this)
    }

    private fun buildDependencyInjector(): Injector {
        return DaggerInjector.builder()
            .defaultModule(DefaultModule())
            .persistenceModule(PersistenceModule(this))
            .build()
    }
}
