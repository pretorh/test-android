package com.pretorh.myapplication

import android.app.Application
import com.pretorh.myapplication.di.DaggerInjector
import com.pretorh.myapplication.di.DefaultModule
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.persistence.MyDatabase
import com.pretorh.myapplication.persistence.buildDatabase

class MyApplication : Application() {
    lateinit var injector: Injector
    lateinit var database: MyDatabase

    override fun onCreate() {
        super.onCreate()
        database = buildDatabase(this)
        injector = buildDependencyInjector()
    }

    private fun buildDependencyInjector(): Injector {
        return DaggerInjector.builder()
            .defaultModule(DefaultModule())
            .build()
    }
}
