package com.pretorh.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel: ViewModel() {
    val r = Random.nextInt(100, 140)

    init {
        Log.d(TAG, "created MainViewModel, r=$r")
    }

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
