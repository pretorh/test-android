package com.pretorh.myapplication.persistence

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "dummy")
data class Dummy(@PrimaryKey val id: Int)

@Dao
interface DummyDao {
    @Query("select * from dummy")
    fun getAll() : List<Dummy>
}
