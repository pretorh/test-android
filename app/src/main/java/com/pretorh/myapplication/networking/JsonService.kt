package com.pretorh.myapplication.networking

import retrofit2.Call
import retrofit2.http.GET

interface JsonService {
    @GET("http://jsonplaceholder.typicode.com/users")
    fun getAll(): Call<List<JsonUser>>
}

data class JsonUser(val username: String)
