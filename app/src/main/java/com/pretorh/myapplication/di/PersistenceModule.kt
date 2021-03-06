package com.pretorh.myapplication.di

import android.content.Context
import com.pretorh.myapplication.persistence.MyDatabase
import com.pretorh.myapplication.persistence.UserRepository
import com.pretorh.myapplication.persistence.buildDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class PersistenceModule(private val myDatabase: MyDatabase) {
    @Provides
    @Singleton
    fun database(): MyDatabase = myDatabase

    @Provides
    fun userRepository(database: MyDatabase, retrofit: Retrofit) =
        UserRepository(database.user(), retrofit)

    companion object {
        fun build(context: Context): PersistenceModule {
            return PersistenceModule(buildDatabase(context))
        }
    }
}
