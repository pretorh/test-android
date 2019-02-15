package com.pretorh.myapplication

import android.app.Application
import com.pretorh.myapplication.persistence.MyDatabase
import com.pretorh.myapplication.persistence.buildDatabase

class MyApplication : Application() {
    lateinit var database: MyDatabase

    override fun onCreate() {
        super.onCreate()
        database = buildDatabase(this)
    }
}
