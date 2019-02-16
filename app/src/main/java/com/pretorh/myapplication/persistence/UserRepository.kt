package com.pretorh.myapplication.persistence

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import retrofit2.Retrofit

class UserRepository(
    private val userDao: UserDao,
    private val retrofit: Retrofit
) {
    val getUser = userDao.getUser(1)
    val getUserFirstName: LiveData<String> = Transformations
        .map(userDao.getUser(1)) {
            it.firstOrNull()?.firstName ?: ""
        }

    @WorkerThread
    fun setUserFirstName(firstName: String) {
        userDao.insert(User(1, firstName))
    }

    @WorkerThread
    fun clearUserFirstName() {
        userDao.delete(1)
    }
}
