package com.pretorh.myapplication.persistence

import android.content.Context
import androidx.room.Room

fun buildDatabase(context: Context): MyDatabase {
    return Room
        .databaseBuilder(context, MyDatabase::class.java, "database-name")
        .build()
}
