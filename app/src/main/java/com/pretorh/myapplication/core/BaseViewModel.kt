package com.pretorh.myapplication.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pretorh.myapplication.MyApplication
import com.pretorh.myapplication.di.Injector

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    abstract fun setupDependencies(injector: Injector)

    protected fun setup() {
        setupDependencies(getMyApplication().injector)
    }

    private fun getMyApplication() = getApplication<MyApplication>()
}
