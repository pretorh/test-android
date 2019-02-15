package com.pretorh.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val r = Random.nextInt(100, 140)
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        Log.d(TAG, "created MainViewModel, r=$r")
    }

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
