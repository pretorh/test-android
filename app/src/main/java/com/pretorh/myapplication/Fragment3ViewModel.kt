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
            liveData.postValue("hello from Fragment3ViewModel")
            asyncTaskTracker.completed()
        }
        return liveData
    }

    fun getText2(): LiveData<String> {
        val liveData2 = MutableLiveData<String>()
        val task = asyncTaskTracker.startWithMultipleComplete()
        Executors.newSingleThreadExecutor().submit {
            var loops = 0
            while (true) {
                Thread.sleep(250)
                liveData2.postValue("hello from Fragment3ViewModel.getText2 ($loops)")
                loops += 1
                if (loops == 1) {
                    // only track as completed the first time (TODO: improve this)
                    task.completed()
                }
            }
        }
        return liveData2
    }
}
