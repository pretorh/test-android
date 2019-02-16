package com.pretorh.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pretorh.myapplication.core.BaseViewModel
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.persistence.UserRepository
import java.util.concurrent.ExecutorService
import kotlin.random.Random

class MainViewModel(application: Application) : BaseViewModel(application) {
    private val repository: UserRepository
    private val executor: ExecutorService

    val r = Random.nextInt(100, 140)
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val firstName: LiveData<String>

    init {
        setup()
        Log.d(TAG, "created MainViewModel, r=$r")
        val dao = (application as MyApplication).database.user()
        repository = UserRepository(dao)
        executor = application.ioExecutor
        firstName = repository.getUserFirstName
    }

    override fun setupDependencies(injector: Injector) {
        injector.inject(this)
    }

    fun setName(name: String) {
        currentName.value = name
        executor.submit { repository.setUserFirstName(name) }
    }

    fun clearFirstName() {
        executor.submit { repository.clearUserFirstName() }
    }

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
