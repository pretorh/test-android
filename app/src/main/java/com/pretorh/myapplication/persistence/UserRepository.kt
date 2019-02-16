package com.pretorh.myapplication.persistence

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pretorh.myapplication.networking.JsonService
import com.pretorh.myapplication.networking.JsonUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun loadFromService() {
        val service = retrofit.create(JsonService::class.java)
        service.getAll().enqueue(object : Callback<List<JsonUser>> {
            override fun onFailure(call: Call<List<JsonUser>>, t: Throwable) {
                // ignored
            }

            override fun onResponse(call: Call<List<JsonUser>>, response: Response<List<JsonUser>>) {
                // todo
            }
        })
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
