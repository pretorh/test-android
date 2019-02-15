package com.pretorh.myapplication.persistence

import androidx.annotation.WorkerThread

class UserRepository(private val userDao: UserDao) {
    val getUser = userDao.getUser(1)

    @WorkerThread
    suspend fun replace(user: User) {
        userDao.insert(user)
    }
}
