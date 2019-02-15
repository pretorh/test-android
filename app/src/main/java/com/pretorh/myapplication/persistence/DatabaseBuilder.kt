package com.pretorh.myapplication.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

fun buildDatabase(context: Context): MyDatabase {
    val v2Upgrade = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `dummy` (`id` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        }
    }

    return Room
        .databaseBuilder(context, MyDatabase::class.java, "database-name")
        .addMigrations(v2Upgrade)
        .build()
}
