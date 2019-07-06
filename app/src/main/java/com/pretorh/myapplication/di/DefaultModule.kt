package com.pretorh.myapplication.di

import com.pretorh.myapplication.core.AsyncTaskTracker
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DefaultModule(private val asyncTaskTracker: AsyncTaskTracker) {
    @Provides
    @Singleton
    fun ioExecutor(): ExecutorService = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    fun retroFit(executorService: ExecutorService): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .client(buildOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(executorService)
            .build()
    }

    @Provides
    @Singleton
    fun provideAsyncTaskTracker() = asyncTaskTracker

    private fun buildOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}
