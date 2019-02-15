package com.pretorh.myapplication

import android.app.Application
import com.pretorh.myapplication.persistence.MyDatabase
import com.pretorh.myapplication.persistence.buildDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApplication : Application() {
    lateinit var database: MyDatabase
    lateinit var ioExecutor: ExecutorService

    override fun onCreate() {
        super.onCreate()
        database = buildDatabase(this)
        ioExecutor = Executors.newSingleThreadExecutor()
    }
}
