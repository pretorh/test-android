package com.pretorh.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pretorh.myapplication.persistence.UserRepository
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    val r = Random.nextInt(100, 140)
    val currentName: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        Log.d(TAG, "created MainViewModel, r=$r")
        val dao = (application as MyApplication).database.user()
        repository = UserRepository(dao)
    }

    companion object {
        val TAG: String by lazy { MainViewModel::class.java.simpleName }
    }
}
