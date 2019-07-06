package com.pretorh.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Fragment3ViewModel : ViewModel() {
    private val liveData: MutableLiveData<String> = MutableLiveData()

    fun getText(): LiveData<String> {
        liveData.value = "hello from Fragment3ViewModel"
        return liveData
    }
}
