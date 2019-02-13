package com.pretorh.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    init {
        Log.d(TAG, "created MainViewModel")
    }

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
