package com.pretorh.myapplication.di

import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DefaultModule {
    @Provides
    @Singleton
    fun ioExecutor(): ExecutorService = Executors.newSingleThreadExecutor()
}
