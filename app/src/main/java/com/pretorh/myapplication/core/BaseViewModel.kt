package com.pretorh.myapplication.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pretorh.myapplication.MyApplication
import com.pretorh.myapplication.di.Injector
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import javax.inject.Inject

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    protected lateinit var executor: ExecutorService

    abstract fun setupDependencies(injector: Injector)

    protected fun setup() {
        setupDependencies(getMyApplication().injector)
    }

    protected fun async(task: () -> Unit): Future<*> = executor.submit(task)

    private fun getMyApplication() = getApplication<MyApplication>()
}
