package com.pretorh.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

class Fragment3ViewModel : ViewModel() {
    private val liveData: MutableLiveData<String> = MutableLiveData()

    fun getText(): LiveData<String> {
        Executors.newSingleThreadExecutor().submit {
            Thread.sleep(250)
            liveData.value = "hello from Fragment3ViewModel"
        }
        return liveData
    }
}
