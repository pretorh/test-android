package com.pretorh.myapplication.di

import android.content.Context
import com.pretorh.myapplication.persistence.DbBackupHelper
import com.pretorh.myapplication.persistence.MyDatabase
import com.pretorh.myapplication.persistence.UserRepository
import com.pretorh.myapplication.persistence.buildDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import javax.inject.Singleton

@Module
class PersistenceModule(private val context: Context) {
    @Provides
    @Singleton
    fun database() = buildDatabase(context)

    @Provides
    @Singleton
    fun dbBackupHelper(database: MyDatabase, executorService: ExecutorService): DbBackupHelper =
        DbBackupHelper(database, executorService, context.filesDir)

    @Provides
    fun userRepository(database: MyDatabase, retrofit: Retrofit) =
        UserRepository(database.user(), retrofit)
}
