package com.pretorh.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.pretorh.myapplication.core.BaseViewModel
import com.pretorh.myapplication.core.SingleHandledEvent
import com.pretorh.myapplication.di.Injector
import com.pretorh.myapplication.persistence.UserRepository
import javax.inject.Inject
import kotlin.random.Random

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var repository: UserRepository

    val r = Random.nextInt(100, 140)
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val firstName: LiveData<String>
    val firstNameEvent: MutableLiveData<SingleHandledEvent<String>>

    init {
        setup()
        Log.d(TAG, "created MainViewModel, r=$r")
        firstName = repository.getUserFirstName
        firstNameEvent = MediatorLiveData()
        firstNameEvent.addSource(repository.getUserFirstName) {
            firstNameEvent.value = SingleHandledEvent(it)
        }
        loadFromNetwork()
    }

    override fun setupDependencies(injector: Injector) {
        injector.inject(this)
    }

    fun setName(name: String) {
        currentName.value = name
        async { repository.setUserFirstName(name) }
    }

    fun clearFirstName() {
        async { repository.clearUserFirstName() }
    }

    fun loadFromNetwork() = repository.loadFromService()

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
