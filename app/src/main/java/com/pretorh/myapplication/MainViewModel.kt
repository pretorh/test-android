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
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class MainViewModel(application: Application) : BaseViewModel(application) {
    @Suppress("ProtectedInFinal")
    @Inject
    protected lateinit var repository: UserRepository

    val r = Random.nextInt(100, 140)
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val firstName: LiveData<String>
    val firstNameEvent: MutableLiveData<SingleHandledEvent<String>>
    val randomNumberGenerator: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        setup()
        Log.d(TAG, "created MainViewModel, r=$r")
        firstName = repository.getUserFirstName
        firstNameEvent = MediatorLiveData()
        firstNameEvent.addSource(repository.getUserFirstName) {
            firstNameEvent.value = SingleHandledEvent(it)
        }
        Executors.newSingleThreadExecutor().submit {
            while (true) {
                Thread.sleep(2500)
                val i = (Math.random() * 10).roundToInt()
                Log.d("MainViewModel", "generated random number $i")
                randomNumberGenerator.postValue(i)
            }
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
