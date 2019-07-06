package com.pretorh.myapplication

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pretorh.myapplication.core.AsyncTaskTracker
import com.pretorh.myapplication.core.BaseViewModel
import com.pretorh.myapplication.di.Injector
import java.util.concurrent.Executors
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class Fragment3ViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    protected lateinit var asyncTaskTracker: AsyncTaskTracker
    private val liveData: MutableLiveData<String> = MutableLiveData()

    init {
        setup()
    }

    override fun setupDependencies(injector: Injector) {
        injector.inject(this)
    }

    fun getText(): LiveData<String> {
        asyncTaskTracker.start()
        Executors.newSingleThreadExecutor().submit {
            Thread.sleep(250)
            liveData.value = "hello from Fragment3ViewModel"
            asyncTaskTracker.completed()
        }
        return liveData
    }
}
