package com.pretorh.myapplication.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 2, entities = [
    User::class, Dummy::class
])
abstract class MyDatabase : RoomDatabase() {
    abstract fun user(): UserDao
}
