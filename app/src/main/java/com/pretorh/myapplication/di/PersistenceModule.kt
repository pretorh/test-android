package com.pretorh.myapplication.di

import android.content.Context
import com.pretorh.myapplication.persistence.buildDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule(private val context: Context) {
    @Provides
    @Singleton
    fun database() = buildDatabase(context)
}
