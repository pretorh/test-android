package com.pretorh.myapplication

import android.content.Context
import androidx.room.Room
import com.pretorh.myapplication.di.PersistenceModule
import com.pretorh.myapplication.persistence.MyDatabase

class InMemoryPersistenceBuilder(context: Context) {
    val myDatabase = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java).build()

    fun module(): PersistenceModule {
        return PersistenceModule(myDatabase)
    }
}
